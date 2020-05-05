package controller;

import org.junit.jupiter.api.Test;
import views.AanbiedenArtikelView;

import static domain.ArtikelSoort.DIENST;
import static domain.ArtikelSoort.PRODUCT;

class AanbiedenArtikelControllerTest {

    AanbiedenArtikelController controller = new AanbiedenArtikelController(new AanbiedenArtikelView());

    @Test
    void testPrintCategorieen() {
        controller.vraagCategorie(PRODUCT);
        controller.vraagCategorie(DIENST);

    }
}