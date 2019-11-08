/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.List;

/**
 *
 * @author Maxime
 */
public class FormateurResource {
    String nom;
    String prenom;
    List<CompetenceResource> competences;

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<CompetenceResource> getCompetences() {
        return competences;
    }

    public void setCompetences(List<CompetenceResource> competences) {
        this.competences = competences;
    }
    
    
}
