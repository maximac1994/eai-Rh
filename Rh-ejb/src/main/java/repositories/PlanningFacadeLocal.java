/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import Entities.Planning;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Maxime
 */
@Local
public interface PlanningFacadeLocal {

    void create(Planning planning);

    void edit(Planning planning);

    void remove(Planning planning);

    Planning find(Object id);

    List<Planning> findAll();

    List<Planning> findRange(int[] range);
List<Planning> getDatesOccupe(int idF);
public List<Planning> getPlanningJourFormateur(Date jour,int idF);
    int count();
    
}
