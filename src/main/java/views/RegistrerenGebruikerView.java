package views;

import controller.RegistrerenGebruikerController;

import domain.Regelement;

public class RegistrerenGebruikerView extends AbstractView {

    public void toon() {
        registreerGebruiker();
    }

    public RegistrerenGebruikerView() {
        super();
    }

    public void registreerGebruiker() {
        // Dit eigenlijk in het hoofdmenu zetten
        new RegistrerenGebruikerController(this).startRegistratie();
    }

    public void toonRegelement() {
        Regelement.toon();
    }
}
