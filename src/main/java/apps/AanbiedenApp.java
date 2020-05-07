package apps;

import controller.AanbiedenArtikelController;
import views.AanbiedenArtikelView;

import static util.GebruikerUtil.setHuidigeGebruikerById;

public class AanbiedenApp {

    public static void main(String[] args) {
        setHuidigeGebruikerById(1); // Simuleer ingelogde gebruiker
        AanbiedenArtikelController controller = new AanbiedenArtikelController(new AanbiedenArtikelView());
        controller.aanbiedenArtikel();
    }
}
