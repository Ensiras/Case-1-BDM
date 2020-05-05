package apps;

import controller.AanbiedenArtikelController;
import domain.AbstractCategorie;
import views.AanbiedenArtikelView;

import static domain.ArtikelSoort.PRODUCT;

public class AanbiedenApp {

    public static void main(String[] args) {
        AanbiedenArtikelController controller = new AanbiedenArtikelController(new AanbiedenArtikelView());
        controller.aanbiedenArtikel();
    }
}
