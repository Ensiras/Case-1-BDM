package resources;

import service.ArtikelService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.*;

@Path("artikelen")
@Produces(APPLICATION_JSON)
@Stateless
public class ArtikelenResource {

    @Inject
    ArtikelService service;

    @POST
    @Path("nieuw")
    public ArtikelInput aanbiedenArtikel(ArtikelInput artikelInput) {
        System.out.println("POST endpoint bereikt!");
        System.out.println(artikelInput.getNaam());
        service.aanbiedenArtikel(artikelInput);
        return artikelInput;
    }

}
