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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maxime
 */
@Entity
@Table(name = "planning")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planning.findAll", query = "SELECT p FROM Planning p")
    , @NamedQuery(name = "Planning.findByIdFormateur", query = "SELECT p FROM Planning p WHERE p.planningPK.idFormateur = :idFormateur")
    , @NamedQuery(name = "Planning.findByJour", query = "SELECT p FROM Planning p WHERE p.planningPK.jour = :jour")
    , @NamedQuery(name = "Planning.findByEtat", query = "SELECT p FROM Planning p WHERE p.etat = :etat")
, @NamedQuery(name = "Planning.findByJourFormateur", query = "SELECT p FROM Planning p WHERE p.planningPK.jour = :jour AND p.planningPK.idFormateur = :idFormateur")})
public class Planning implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlanningPK planningPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "etat")
    private String etat;

    public Planning() {
    }

    public Planning(PlanningPK planningPK) {
        this.planningPK = planningPK;
    }

    public Planning(PlanningPK planningPK, String etat) {
        this.planningPK = planningPK;
        this.etat = etat;
    }

    public Planning(int idFormateur, Date jour) {
        this.planningPK = new PlanningPK(idFormateur, jour);
    }

    public PlanningPK getPlanningPK() {
        return planningPK;
    }

    public void setPlanningPK(PlanningPK planningPK) {
        this.planningPK = planningPK;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planningPK != null ? planningPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planning)) {
            return false;
        }
        Planning other = (Planning) object;
        if ((this.planningPK == null && other.planningPK != null) || (this.planningPK != null && !this.planningPK.equals(other.planningPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Planning[ planningPK=" + planningPK + " ]";
    }
    
}
