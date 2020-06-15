package resources;


import service.GebruikerService;

import javax.ejb.Stateless;

import javax.inject.Inject;
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
    GebruikerService service;

    @GET
    public String testGet() {
        return "Het werkt";
    }

    @POST
    @Path("nieuw")
    @Produces(MediaType.APPLICATION_JSON)
    public GebruikerInput registrerenGebruiker(GebruikerInput gebruikerIn) {
        System.out.println("Gebruiker ontvangen in POST endpoint.");
        service.registreerGebruiker(gebruikerIn);
        return gebruikerIn;
    }
}
