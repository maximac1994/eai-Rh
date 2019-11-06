/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Maxime
 */
@Embeddable
public class NiveauPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idFormateur")
    private int idFormateur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCompetence")
    private int idCompetence;

    public NiveauPK() {
    }

    public NiveauPK(int idFormateur, int idCompetence) {
        this.idFormateur = idFormateur;
        this.idCompetence = idCompetence;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public int getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(int idCompetence) {
        this.idCompetence = idCompetence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFormateur;
        hash += (int) idCompetence;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NiveauPK)) {
            return false;
        }
        NiveauPK other = (NiveauPK) object;
        if (this.idFormateur != other.idFormateur) {
            return false;
        }
        if (this.idCompetence != other.idCompetence) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.NiveauPK[ idFormateur=" + idFormateur + ", idCompetence=" + idCompetence + " ]";
    }
    
}
