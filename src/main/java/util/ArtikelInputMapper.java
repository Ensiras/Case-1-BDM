package util;

import domain.AbstractArtikel;
import domain.Bezorgwijze;
import domain.Product;
import domain.ProductCategorie;
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
    GebruikerService service;

    public <T extends AbstractArtikel> T mapArtikelInputToArtikelEntity(ArtikelInput artikelInput) {
        if (artikelInput.getSoort().equals("Product")) { // Should probably check for null first
            return (T) mapArtikelInputToProductEntity(artikelInput);
        };
        return null;
    }

    public Product mapArtikelInputToProductEntity(ArtikelInput artikelInput) {
        Product productUit = new Product();
        productUit.setNaam(artikelInput.getNaam());
        productUit.setPrijs(BigDecimal.valueOf(artikelInput.getPrijs()));
        productUit.setAanbieder(service.zoek(1)); // TODO: implement that actual user from frontend is 'found'
        productUit.setBezorgwijzen(mapBezorgwijzen(artikelInput));
        productUit.setProductCategorie(mapCategorieInputToCategorieEntity(artikelInput));
//        product.setBijlagen(artikelInput.getBijlagen()); TODO: implement bijlagen
        return productUit;
    }

    public static Set<Bezorgwijze> mapBezorgwijzen(ArtikelInput artikelInput) {
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

    private ProductCategorie mapCategorieInputToCategorieEntity(ArtikelInput artikelInput) {
        String categorieNaam = artikelInput.getCategorie();
        return new ProductCategorie(categorieNaam, "testomschrijving");
    }
}
