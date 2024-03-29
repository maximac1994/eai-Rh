/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import Entities.Niveau;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maxime
 */
@Stateless
public class NiveauFacade extends AbstractFacade<Niveau> implements NiveauFacadeLocal {

    @PersistenceContext(unitName = "ProjetJEE_Rh-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NiveauFacade() {
        super(Niveau.class);
    }
    
    public List<Niveau> getNiveaux(int idF){
        return(
        em.createNamedQuery("Niveau.findByIdFormateur")
                .setParameter("idFormateur", idF)
                .getResultList());
    }
    
}
