/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entities.Competence;
import com.google.gson.Gson;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import services.CompetencesServiceLocal;

/**
 *
 * @author Maxime
 */
@Path("competence")
public class CompetenceRest {
     @Context
    private UriInfo context;
    
    CompetencesServiceLocal competencesService;
    Gson gson;
    public CompetenceRest(){
        this.gson = new Gson();
        this.competencesService = lookupBanqueBeanLocal();
    }
     @GET
    public String getFormateurs() {
        List<Competence> liste = competencesService.getCompetences();
        return gson.toJson(liste);
    }
   
     private CompetencesServiceLocal lookupBanqueBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CompetencesServiceLocal) c.lookup("java:global/Rh-ear/Rh-ejb-1.0-SNAPSHOT/CompetencesService!services.CompetencesServiceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
