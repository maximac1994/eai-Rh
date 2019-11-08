/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Buisiness.GestionCompetences;
import Buisiness.GestionCompetencesLocal;
import Entities.Competence;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Maxime
 */
@Stateless
public class CompetencesService implements CompetencesServiceLocal {
    @EJB
    GestionCompetencesLocal gcl;
    @Override
    public void addCompetence(String nom) {
      gcl.addCompetence("BigData");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Competence> getCompetences() {
      return gcl.getCompetences();
    }
}
