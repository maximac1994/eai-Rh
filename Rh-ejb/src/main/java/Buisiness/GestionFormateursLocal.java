/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buisiness;

import Entities.Formateur;
import MessagesTypes.DemandeRessources;
import MessagesTypes.EvenementFormation;
import MessagesTypes.EvenementFormationAnnulation;
import MessagesTypes.EvenementFormationChangeEtat;
import exceptions.OccupedFormateurException;
import exceptions.UnknownFormateurException;
import java.util.List;
import javax.ejb.Local;
import resources.CompetenceResource;

/**
 * gestion des formateurs
 * @author Maxime
 */
@Local
public interface GestionFormateursLocal {

    /**
     * lister tous les formateurs
     * @return
     */
    public List<Formateur> getFormateurs();

    /**
     * ajouter une formateur et lui affecter des compétences
     * @param nom
     * @param prenom
     * @param competences
     */
    public void addFormateur(String nom, String prenom,List<CompetenceResource> competences);

    /**
     * supprimer un formateur
     * @param id
     * @throws UnknownFormateurException
     * @throws OccupedFormateurException
     */
    public void removeFormateur(int id) throws UnknownFormateurException,OccupedFormateurException;

    /**
     * envoyer la liste des formateurs qui correspondent à des criteres définis dans dr.
     * @param dr
     */
    public void sendListFormateurs(DemandeRessources dr);

    /**
     * desafecter un formateur d'une période
     * @param efa
     */
    public void removeState(EvenementFormationAnnulation efa);

    /**
     * changer l'etat d'un formateur sur une période
     * @param efa
     * @param etat
     */
    public void changeState(EvenementFormationChangeEtat efa,String etat);
}
