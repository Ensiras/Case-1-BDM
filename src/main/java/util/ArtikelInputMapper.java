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
public class ArtikelInputMapper {

    @Inject
    GebruikerService gebruikerService;

    public ArtikelInputMapper() {
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractArtikel> T mapArtikelInputToArtikelEntity(ArtikelInput artikelInput) {
        if (artikelInput.getSoort().equals("Product")) { // Should probably check for null first
            return (T) mapArtikelInputToProductEntity(artikelInput);
        } else {
            return (T) mapArtikelInputToDienstEntity(artikelInput);
        }
    }

    Dienst mapArtikelInputToDienstEntity(ArtikelInput artikelInput) {
        Dienst dienstUit = new Dienst();
        dienstUit.setNaam(artikelInput.getNaam());
        dienstUit.setPrijs(BigDecimal.valueOf(artikelInput.getPrijs()));

        Gebruiker gebruiker = gebruikerService.zoek(artikelInput.getGebruikerId()); // TODO: als er geen gebruiker wordt gevonden --> probleem
        System.out.println(gebruiker);
        dienstUit.setAanbieder(gebruiker);
        dienstUit.setDienstCategorie(mapCategorieInputToDienstCategorieEntity(artikelInput));
        dienstUit.setOmschrijving(artikelInput.getOmschrijving());
        return dienstUit;
    }

    Product mapArtikelInputToProductEntity(ArtikelInput artikelInput) {
        Product productUit = new Product();
        productUit.setNaam(artikelInput.getNaam());
        productUit.setPrijs(BigDecimal.valueOf(artikelInput.getPrijs()));

        Gebruiker gebruiker = gebruikerService.zoek(artikelInput.getGebruikerId());
        System.out.println(gebruiker);
        productUit.setAanbieder(gebruiker);
        productUit.setBezorgwijzen(mapBezorgwijzen(artikelInput));
        productUit.setProductCategorie(mapCategorieInputToCategorieEntity(artikelInput));
        productUit.setOmschrijving(artikelInput.getOmschrijving());
//        product.setBijlagen(artikelInput.getBijlagen()); TODO: implement bijlagen
        return productUit;
    }

    static Set<Bezorgwijze> mapBezorgwijzen(ArtikelInput artikelInput) {
        Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();

        if (artikelInput.isBezorgAfhalenMagazijn()) {
            bezorgwijzen.add(AFHALEN_MAGAZIJN);
        }
        if (artikelInput.isBezorgAfhalenThuis()) {
            bezorgwijzen.add(AFHALEN_THUIS);
        }
        if (artikelInput.isBezorgVersturenVooruit()) {
            bezorgwijzen.add(VERSTUREN_VOORBET);
        }
        if (artikelInput.isBezorgVersturenRembours()) {
            bezorgwijzen.add(VERSTUREN_REMBOURS);
        }

        return bezorgwijzen;
    }

    // TODO: misschien toch laten zoeken naar bestaande categorieën.
    private ProductCategorie mapCategorieInputToCategorieEntity(ArtikelInput artikelInput) {
        String categorieNaam = artikelInput.getCategorie();
        return new ProductCategorie(categorieNaam, "testomschrijving");
    }

    private DienstCategorie mapCategorieInputToDienstCategorieEntity(ArtikelInput artikelInput) {
        String categorieNaam = artikelInput.getCategorie();
        return new DienstCategorie(categorieNaam, "testomschrijving");
    }
}
