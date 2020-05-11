package views;

import controller.AanbiedenArtikelController;

import static util.GebruikerUtil.huidigeGebruiker;
import static util.GebruikerUtil.setHuidigeGebruikerById;

public class AanbiedenArtikelView extends AbstractView {

    public AanbiedenArtikelView() {
        super();
    }

    @Override
    public void toon() {
        setHuidigeGebruikerById(1); // Methode om ingelogde gebruiker te simuleren
        toonBericht("Nieuw artikel aanbieden als gebruiker: " + huidigeGebruiker.getEmail());
        new AanbiedenArtikelController(this).aanbiedenArtikel();
    }

}
