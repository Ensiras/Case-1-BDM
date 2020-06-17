package resources;


import service.GebruikerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("gebruikers")

@Stateless
public class GebruikersResource {

    @Inject
    GebruikerService service;

    @POST
    @Path("nieuw")
    @Produces(MediaType.APPLICATION_JSON)
    public GebruikerInput registrerenGebruiker(GebruikerInput gebruikerIn) {
        service.registreerGebruiker(gebruikerIn);
        return gebruikerIn;
    }
}
