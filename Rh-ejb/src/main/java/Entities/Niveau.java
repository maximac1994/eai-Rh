/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maxime
 */
@Entity
@Table(name = "niveau")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Niveau.findAll", query = "SELECT n FROM Niveau n")
    , @NamedQuery(name = "Niveau.findByIdFormateur", query = "SELECT n FROM Niveau n WHERE n.niveauPK.idFormateur = :idFormateur")
    , @NamedQuery(name = "Niveau.findByIdCompetence", query = "SELECT n FROM Niveau n WHERE n.niveauPK.idCompetence = :idCompetence")
    , @NamedQuery(name = "Niveau.findByNiveau", query = "SELECT n FROM Niveau n WHERE n.niveau = :niveau")})
public class Niveau implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NiveauPK niveauPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "niveau")
    private int niveau;

    public Niveau() {
    }

    public Niveau(NiveauPK niveauPK) {
        this.niveauPK = niveauPK;
    }

    public Niveau(NiveauPK niveauPK, int niveau) {
        this.niveauPK = niveauPK;
        this.niveau = niveau;
    }

    public Niveau(int idFormateur, int idCompetence) {
        this.niveauPK = new NiveauPK(idFormateur, idCompetence);
    }

    public NiveauPK getNiveauPK() {
        return niveauPK;
    }

    public void setNiveauPK(NiveauPK niveauPK) {
        this.niveauPK = niveauPK;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (niveauPK != null ? niveauPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Niveau)) {
            return false;
        }
        Niveau other = (Niveau) object;
        if ((this.niveauPK == null && other.niveauPK != null) || (this.niveauPK != null && !this.niveauPK.equals(other.niveauPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Niveau[ niveauPK=" + niveauPK + " ]";
    }
    
}
