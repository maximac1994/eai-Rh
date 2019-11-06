/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import Entities.Niveau;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Maxime
 */
@Local
public interface NiveauFacadeLocal {

    void create(Niveau niveau);

    void edit(Niveau niveau);

    void remove(Niveau niveau);

    Niveau find(Object id);

    List<Niveau> findAll();

    List<Niveau> findRange(int[] range);

    int count();
    
}
