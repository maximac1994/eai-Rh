/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Competence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competence.findAll", query = "SELECT c FROM Competence c")
    , @NamedQuery(name = "Competence.findByIdCompetence", query = "SELECT c FROM Competence c WHERE c.idCompetence = :idCompetence")
    , @NamedQuery(name = "Competence.findByNomCompetence", query = "SELECT c FROM Competence c WHERE c.nomCompetence = :nomCompetence")})
public class Competence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCompetence")
    private Integer idCompetence;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomCompetence")
    private String nomCompetence;

    public Competence() {
    }

    public Competence(Integer idCompetence) {
        this.idCompetence = idCompetence;
    }

    public Competence(Integer idCompetence, String nomCompetence) {
        this.idCompetence = idCompetence;
        this.nomCompetence = nomCompetence;
    }

    public Integer getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(Integer idCompetence) {
        this.idCompetence = idCompetence;
    }

    public String getNomCompetence() {
        return nomCompetence;
    }

    public void setNomCompetence(String nomCompetence) {
        this.nomCompetence = nomCompetence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompetence != null ? idCompetence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.idCompetence == null && other.idCompetence != null) || (this.idCompetence != null && !this.idCompetence.equals(other.idCompetence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Competence[ idCompetence=" + idCompetence + " ]";
    }
    
}
