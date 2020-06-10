package resources;


import service.RegistrerenGebruikerService;
import util.TEMP.TEMPGebruikerWrapper;
import views.RegistrerenGebruikerView;

import javax.ejb.Stateless;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("gebruikers")

@Stateless
public class GebruikersResource {

    @Inject
    RegistrerenGebruikerService service;

    @GET
    public String testGet() {
        return "Het werkt";
    }

    @POST
    @Path("nieuw")
    @Produces(MediaType.APPLICATION_JSON)
    public TEMPGebruikerWrapper registrerenGebruiker(TEMPGebruikerWrapper gebruiker) {
        boolean b = service.checkEmail(gebruiker);
        System.out.println(b);
        return gebruiker;
    }
}
