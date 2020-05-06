package apps;

import controller.AanbiedenArtikelController;
import views.AanbiedenArtikelView;

public class AanbiedenApp {

    public static void main(String[] args) {
        AanbiedenArtikelController controller = new AanbiedenArtikelController(new AanbiedenArtikelView());
        controller.aanbiedenArtikel();
    }
}
