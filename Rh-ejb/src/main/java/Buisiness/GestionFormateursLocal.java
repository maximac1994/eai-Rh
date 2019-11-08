/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buisiness;

import Entities.Formateur;
import java.util.HashMap;
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
}
