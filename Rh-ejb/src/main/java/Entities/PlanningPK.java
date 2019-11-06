/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Maxime
 */
@Embeddable
public class PlanningPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idFormateur")
    private int idFormateur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jour")
    @Temporal(TemporalType.DATE)
    private Date jour;

    public PlanningPK() {
    }

    public PlanningPK(int idFormateur, Date jour) {
        this.idFormateur = idFormateur;
        this.jour = jour;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFormateur;
        hash += (jour != null ? jour.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanningPK)) {
            return false;
        }
        PlanningPK other = (PlanningPK) object;
        if (this.idFormateur != other.idFormateur) {
            return false;
        }
        if ((this.jour == null && other.jour != null) || (this.jour != null && !this.jour.equals(other.jour))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PlanningPK[ idFormateur=" + idFormateur + ", jour=" + jour + " ]";
    }
    
}
