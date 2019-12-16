/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Formateur;
import exceptions.OccupedFormateurException;
import exceptions.UnknownFormateurException;
import java.util.List;
import javax.ejb.Local;
import resources.FormateurResource;

/**
 * Exposition des services RH portant sur les formateurs en REST
 * @author Maxime
 */
@Local
public interface FormateurServiceLocal {

    /**
     * lister les formateurs
     * @return
     */
    public List<Formateur> getFormateurs();

    /**
     * ajout d'un formateur
     * @param formateurResource
     */
    public void addFormateur(FormateurResource formateurResource);

    /**
     * suppression d'un formateur
     * @param id
     * @throws UnknownFormateurException
     * @throws OccupedFormateurException
     */
    public void removeFormateur(int id) throws  UnknownFormateurException,OccupedFormateurException;
}
