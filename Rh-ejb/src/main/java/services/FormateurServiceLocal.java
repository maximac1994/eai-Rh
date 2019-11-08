/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Formateur;
import java.util.List;
import javax.ejb.Local;
import resources.CompetenceResource;
import resources.FormateurResource;

/**
 *
 * @author Maxime
 */
@Local
public interface FormateurServiceLocal {
    public List<Formateur> getFormateurs();
    public void addFormateur(FormateurResource formateurResource);
}
