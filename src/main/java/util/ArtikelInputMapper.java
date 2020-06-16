package util;

import domain.*;
import resources.ArtikelInput;
import service.GebruikerService;


import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import static domain.Bezorgwijze.*;
import static domain.Bezorgwijze.VERSTUREN_REMBOURS;

@Stateless
public class ArtikelInputMapper implements InputMapper<ArtikelInput, AbstractArtikel> {

    @Inject
    GebruikerService gebruikerService;

    @Inject
    BezorgwijzenMapper bezorgwijzenMapper;

    public ArtikelInputMapper() {
    }

    @Override
    public AbstractArtikel mapFromInputToEntity(ArtikelInput artikelInput) {
        String soort = artikelInput.getSoort();
        String naam = artikelInput.getNaam();
        BigDecimal prijs = BigDecimal.valueOf(artikelInput.getPrijs());
        String categorie = artikelInput.getCategorie();
        String omschrijving = artikelInput.getOmschrijving();
        Gebruiker gebruiker = gebruikerService.zoek(artikelInput.getGebruikerId());

        if (soort.equals("Product")) {
            Product product = new Product(naam, gebruiker, prijs, categorie, omschrijving);
            product.setBezorgwijzen(bezorgwijzenMapper.mapBezorgwijzen(artikelInput));
            return product;
        } else if (soort.equals("Dienst")) {
            return new Dienst(naam, gebruiker, prijs, categorie, omschrijving);
        }
        return null;
    }
}
