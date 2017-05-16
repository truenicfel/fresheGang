/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.auth.requesthandler;

import edu.hm.cs.schnitzel.media.entities.Book;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author nicfel
 */
@Path("/auth")
public class AuthRequestHandler {

//    @POST
//    @Path("/books")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createBook(Book b) {
//        System.out.println("got called");
//        MediaServiceResult res = mediaService.addBook(b);
//        // wird wohl automatisch von JSON zu nem Objekt umgewandelt...
//        JSONObject jo = new JSONObject();
//        jo.put("detail", res.getStatus());
//        jo.put("code", res.getCode());
//        return Response
//                .status(res.getCode())
//                .entity(jo.toString())
//                .build();
//    }

    @POST
    @Path("/token")
    public Response validateToken() {
        
    }
    @POST
    @Path("")
    public Response requestToken() {
        
    }
    
}
