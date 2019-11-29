/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entities.Formateur;
import com.google.gson.Gson;
import exceptions.UnknownFormateurException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import resources.FormateurResource;
import services.FormateurServiceLocal;

/**
 *
 * @author Maxime
 */
@Path("formateur")
public class FormateurRest {
    @Context
    private UriInfo context;
    
    FormateurServiceLocal formateurService;
    
    Gson gson;

    public FormateurRest(){
       this.gson = new Gson();
       formateurService = lookupBanqueBeanLocal();
    }
    
     @GET
    public Response getFormateurs() {
        List<Formateur> liste = formateurService.getFormateurs();
        return Response.ok(gson.toJson(liste)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFormateur(String formateur) {
        FormateurResource fr = gson.fromJson(formateur, FormateurResource.class);
        formateurService.addFormateur(fr);
        return Response.ok("Formateur ajouté avec succès").build();
    }
    
    @DELETE
    public Response removeFormateur(@QueryParam("idFormateur") int idFormateur) {
        try {
            formateurService.removeFormateur(idFormateur);
        } catch (UnknownFormateurException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity("le formateur n'existe pas").build();
        }
        return Response.ok("Formateur supprimé avec succes").build();
    }
    
    
    
     private FormateurServiceLocal lookupBanqueBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (FormateurServiceLocal) c.lookup("java:global/Rh-ear/Rh-ejb-1.0-SNAPSHOT/FormateurService!services.FormateurServiceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
   
}
