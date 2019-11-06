/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buisiness;

import Entities.Competence;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import repositories.CompetenceFacadeLocal;

/**
 *
 * @author Maxime
 */
@Stateless
public class GestionCompetences implements GestionCompetencesLocal {

   @EJB
    CompetenceFacadeLocal cf;
    @Override
     public void addCompetence(String nom){
        Competence c = new Competence();
        c.setNomCompetence(nom);
        cf.create(c);
    }
}
