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
@Table(name = "Formateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formateur.findAll", query = "SELECT f FROM Formateur f")
    , @NamedQuery(name = "Formateur.findByIdFormateur", query = "SELECT f FROM Formateur f WHERE f.idFormateur = :idFormateur")
    , @NamedQuery(name = "Formateur.findByNomFormateur", query = "SELECT f FROM Formateur f WHERE f.nomFormateur = :nomFormateur")
    , @NamedQuery(name = "Formateur.findByPrenomFormateur", query = "SELECT f FROM Formateur f WHERE f.prenomFormateur = :prenomFormateur")})
public class Formateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFormateur")
    private Integer idFormateur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomFormateur")
    private String nomFormateur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "prenomFormateur")
    private String prenomFormateur;

    public Formateur() {
    }

    public Formateur(Integer idFormateur) {
        this.idFormateur = idFormateur;
    }

    public Formateur(Integer idFormateur, String nomFormateur, String prenomFormateur) {
        this.idFormateur = idFormateur;
        this.nomFormateur = nomFormateur;
        this.prenomFormateur = prenomFormateur;
    }

    public Integer getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(Integer idFormateur) {
        this.idFormateur = idFormateur;
    }

    public String getNomFormateur() {
        return nomFormateur;
    }

    public void setNomFormateur(String nomFormateur) {
        this.nomFormateur = nomFormateur;
    }

    public String getPrenomFormateur() {
        return prenomFormateur;
    }

    public void setPrenomFormateur(String prenomFormateur) {
        this.prenomFormateur = prenomFormateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormateur != null ? idFormateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formateur)) {
            return false;
        }
        Formateur other = (Formateur) object;
        if ((this.idFormateur == null && other.idFormateur != null) || (this.idFormateur != null && !this.idFormateur.equals(other.idFormateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Formateur[ idFormateur=" + idFormateur + " ]";
    }
    
}
