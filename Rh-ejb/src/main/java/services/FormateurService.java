/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Buisiness.GestionFormateursLocal;
import Entities.Formateur;
import exceptions.UnknownFormateurException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import resources.CompetenceResource;
import resources.FormateurResource;

/**
 *
 * @author Maxime
 */
@Stateless
public class FormateurService implements FormateurServiceLocal {
@EJB
GestionFormateursLocal gfl;

    @Override
    public List<Formateur> getFormateurs() {
     return gfl.getFormateurs();
    }

    @Override
    public void addFormateur(FormateurResource fr) {
     gfl.addFormateur(fr.getNom(), fr.getPrenom(), fr.getCompetences());
    }

    @Override
    public void removeFormateur(int id) throws  UnknownFormateurException{
        gfl.removeFormateur(id);
    }

   
}
