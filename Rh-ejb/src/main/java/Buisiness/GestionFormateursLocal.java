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
import exceptions.UnknownFormateurException;
import java.util.List;
import javax.ejb.Local;
import resources.CompetenceResource;

/**
 *
 * @author Maxime
 */
@Local
public interface GestionFormateursLocal {
    public List<Formateur> getFormateurs();
    public void addFormateur(String nom, String prenom,List<CompetenceResource> competences);
    public void removeFormateur(int id)  throws UnknownFormateurException;
    public void sendListFormateurs(DemandeRessources dr);
    public void removeState(EvenementFormationAnnulation efa);
    public void changeState(EvenementFormationChangeEtat efa,String etat);
}
