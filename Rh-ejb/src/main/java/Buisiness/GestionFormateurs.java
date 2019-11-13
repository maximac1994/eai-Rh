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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import repositories.FormateurFacadeLocal;
import repositories.NiveauFacadeLocal;
import resources.CompetenceResource;

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
    
    @Override
    public List<Formateur> getFormateurs() {
        return ffl.findAll();
    }
    
    @Override
    public void addFormateur(String nom, String prenom, List<CompetenceResource> competences) {
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
    public void removeFormateur(int id) throws UnknownFormateurException{
      Formateur f = ffl.find(id);
      if(f==null){
          throw new UnknownFormateurException();
      }
      ffl.remove(f);
    }
    
}
