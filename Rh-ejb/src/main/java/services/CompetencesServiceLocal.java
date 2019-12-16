/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Competence;
import java.util.List;
import javax.ejb.Local;

/**
 * exposition des traitement de RH en REST
 * @author Maxime
 */
@Local
public interface CompetencesServiceLocal {

    /**
     * ajout competence
     * @param nom
     */
    public void addCompetence(String nom);

    /**
     * lister competences
     * @return
     */
    public List<Competence> getCompetences();
}
