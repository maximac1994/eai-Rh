/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service pour l'ensemble des comptes
 *
 * @author Cédric Teyssié
 */
@Path("test")
public class Test {

    
   /* BanqueBeanLocal banqueBean;*/

    @Context
    private UriInfo context;


    /**
     * Constructeur Ressource
     */
 /*   public Test() {
        this.gson = new Gson();
        this.banqueBean = lookupBanqueBeanLocal();
    }*/

    /**
     * Pas d'export de la liste des comptes
     *
     * @return une réponse HTTP avec le code d'erreur 403
     */
    @GET
    public String getJson() {
        return "test";
    }

   
}
