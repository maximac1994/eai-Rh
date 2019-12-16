/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buisiness;

import Entities.Competence;
import java.util.List;
import javax.ejb.Local;

/**
 * Gestion des compétences des formateurs
 * @author Maxime
 */
@Local
public interface GestionCompetencesLocal {

    /**
     * ajoute une compétence à la liste des competences possibles
     * @param nom
     */
    public void addCompetence(String nom);

    /**
     * liste toutes les compétences possibles
     * @return
     */
    public List<Competence> getCompetences();
}
