/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buisiness;

import javax.ejb.Local;

/**
 *
 * @author Maxime
 */
@Local
public interface GestionCompetencesLocal {
    public void addCompetence(String nom);
}
