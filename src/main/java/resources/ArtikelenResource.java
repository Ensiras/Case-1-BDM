package resources;

import domain.AbstractArtikel;
import service.ArtikelService;
import service.BijlageService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.io.File;

import static javax.ws.rs.core.MediaType.*;

@Path("artikelen")
@Produces(APPLICATION_JSON)
@Stateless
public class ArtikelenResource {

    @Inject
    private ArtikelService artikelService;

    @Inject
    private BijlageService bijlageService;

    @POST
    @Path("nieuw")
    public ArtikelInput aanbiedenArtikel(ArtikelInput artikelInput) {
        System.out.println("POST endpoint bereikt!");
        System.out.println(artikelInput.getNaam());
        AbstractArtikel artikelEntity = artikelService.aanbiedenArtikel(artikelInput);
        artikelInput.setId(artikelEntity.getId()); // TODO: dit stukje code verplaatsen naar service
        return artikelInput;
    }

    @POST
    @Path("/nieuw/bijlage")
    public String aanbiedenNieuweBijlage(
            @QueryParam("bijlagenaam") String bijlageNaam,
            @QueryParam("bijlagetype") String bijlageType,
            @QueryParam("artikelid") String artikelId,
            File data) {

        System.out.println("Bijlage POST endpoint bereikt!");
        System.out.println("Artikel id is: " + artikelId);
        bijlageService.verwerkNieuweBijlage(data, bijlageNaam, bijlageType, artikelId);
        return "Het is gelukt!";
    }

}
