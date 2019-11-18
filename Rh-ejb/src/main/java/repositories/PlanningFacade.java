/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import Entities.Planning;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maxime
 */
@Stateless
public class PlanningFacade extends AbstractFacade<Planning> implements PlanningFacadeLocal {

    @PersistenceContext(unitName = "ProjetJEE_Rh-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanningFacade() {
        super(Planning.class);
    }
    
    public List<Planning> getDatesOccupe(int idF){
    return(em.createNamedQuery("Planning.findByIdFormateur")
                .setParameter("idFormateur", idF)
                .getResultList());
    }
    
}
