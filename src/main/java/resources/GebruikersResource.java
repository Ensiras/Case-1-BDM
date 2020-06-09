package resources;

import util.TEMP.TEMPGebruikerWrapper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("gebruikers")

public class GebruikersResource {

    @GET
    public String testGet() {
        return "Het werkt";
    }

    @POST
    @Path("nieuw")
    @Produces(MediaType.APPLICATION_JSON)
    public TEMPGebruikerWrapper registrerenGebruiker(TEMPGebruikerWrapper gebruiker) {
        System.out.println(gebruiker.getEmail() + gebruiker.getStraat());
        return gebruiker;
    }
}
