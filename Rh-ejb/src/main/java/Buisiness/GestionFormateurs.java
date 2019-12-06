/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buisiness;

import exceptions.UnknownFormateurException;
import Entities.Formateur;
import Entities.Niveau;
import Entities.NiveauPK;
import Entities.Planning;
import Entities.PlanningPK;
import MessagesTypes.CompetenceNec;
import MessagesTypes.DemandeRessources;
import MessagesTypes.EvenementFormationAnnulation;
import MessagesTypes.EvenementFormationChangeEtat;
import MessagesTypes.FormateurComp;
import MessagesTypes.ListeFormateursCompatibles;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import repositories.FormateurFacadeLocal;
import repositories.NiveauFacadeLocal;
import repositories.PlanningFacadeLocal;
import resources.CompetenceResource;
import senders.FileListeRessources;

/**
 *
 * @author Maxime
 */
@Stateless
public class GestionFormateurs implements GestionFormateursLocal {

    @EJB
    FormateurFacadeLocal ffl;
    @EJB
    NiveauFacadeLocal nfl;
    @EJB
    PlanningFacadeLocal pfl;

    FileListeRessources flr;

    Logger logger;

    public GestionFormateurs() {
        flr = new FileListeRessources();
        logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public List<Formateur> getFormateurs() {
        return ffl.findAll();
    }

    @Override
    public void addFormateur(String nom, String prenom, List<CompetenceResource> competences) {
        logger.log(Level.INFO, "[RH] : ajout d'un formateur");
        Formateur f = new Formateur();
        f.setNomFormateur(nom);
        f.setPrenomFormateur(prenom);
        ffl.create(f);

        for (CompetenceResource cr : competences) {
            Niveau n = new Niveau();
            NiveauPK npk = new NiveauPK();
            npk.setIdCompetence(cr.getId());
            npk.setIdFormateur(f.getIdFormateur());
            n.setNiveauPK(npk);
            n.setNiveau(cr.getNiveau());
            nfl.create(n);
        }

    }

    @Override
    public void removeFormateur(int id) throws UnknownFormateurException {
        logger.log(Level.INFO, "[RH] : suppression d'un formateur");
        Formateur f = ffl.find(id);
        System.out.println("---- " + f);
        if (f != null) {
            ffl.remove(f);
        } else {
            throw new UnknownFormateurException();
        }
    }

    @Override
    public void sendListFormateurs(DemandeRessources dr) {
        logger.log(Level.INFO, "[RH] (gestion) : envoi liste formateur");
        List<CompetenceNec> listeComp = dr.getCompetencesNecessaires();
        List<Niveau> nf = nfl.findAll();
        List<Formateur> lf = ffl.findAll();
        ListeFormateursCompatibles lfc = new ListeFormateursCompatibles();
        lfc.setIdInstance(dr.getIdInstance());
        for (Formateur f : lf) {
            boolean isOk = true;
            List<Niveau> ln = nfl.getNiveaux(f.getIdFormateur());
            boolean formateurOk = true;
            for (CompetenceNec cn : listeComp) {
                boolean fHasCn = false;
                for (Niveau n : ln) {
                    if (n.getNiveauPK().getIdCompetence() == cn.getIdCompetence()) {
                        if (n.getNiveau() >= cn.getNiveau()) {
                            fHasCn = true;
                        }
                    }
                }
                formateurOk = formateurOk && fHasCn;
            }
            if (formateurOk) {
                FormateurComp fc = new FormateurComp();
                fc.setIdFormateur(f.getIdFormateur());
                fc.setDatesOccupees(getDateOccupees(fc));
                lfc.getFormateursCompatibles().add(fc);
            }
        }
        flr.sendListeResources(lfc);
    }

    private List<Date> getDateOccupees(FormateurComp fc) {
        logger.log(Level.INFO, "[RH] : recuperation des dates occuppees");
        List<Planning> lp = pfl.getDatesOccupe(fc.getIdFormateur());
        List<Date> listeToReturn = new ArrayList<Date>();
        for (Planning p : lp) {
            listeToReturn.add(p.getPlanningPK().getJour());
        }
        return listeToReturn;
    }

    @Override
    public void changeState(EvenementFormationChangeEtat efa, String etat) {
        logger.log(Level.INFO, "[RH] : changement de l'etat de la formation");
        Date dateJour = efa.getDateDebut();
        int days = efa.getDuree();

        while (days > 0) {
            DateFormat df = new SimpleDateFormat("EEEE");
            String day = df.format(dateJour);
            if ((!"samedi".equals(day)) && (!"dimanche".equals(day))) {
                List<Planning> lp = pfl.getPlanningJourFormateur(dateJour, efa.getIdFormateur());
                if (lp.isEmpty()) {
                    Planning nP = new Planning();
                    nP.setEtat(etat);
                    PlanningPK pPK = new PlanningPK();
                    pPK.setIdFormateur(efa.getIdFormateur());
                    pPK.setJour(dateJour);
                    nP.setPlanningPK(pPK);
                    pfl.create(nP);
                } else {
                    lp.get(0).setEtat(etat);
                }

                days--;

            }

            dateJour.setTime(dateJour.getTime() + (25 * 3600 * 1000));
        }
    }

    @Override
    public void removeState(EvenementFormationAnnulation efa) {
        logger.log(Level.INFO, "[RH] : supression de l'état");
        Date dateJour = efa.getDateDebut();
        int days = efa.getDuree();
        while (days > 0) {
            DateFormat df = new SimpleDateFormat("EEEE");
            String day = df.format(dateJour);
            if ((!"samedi".equals(day)) && (!"dimanche".equals(day))) {
                List<Planning> lp = pfl.getPlanningJourFormateur(dateJour, efa.getIdFormateur());
                for (Planning p : lp) {
                    System.out.println(p.getEtat());
                    pfl.remove(p);
                }
                days--;
            }
            dateJour.setTime(dateJour.getTime() + (25 * 3600 * 1000));
        }
    }

}
