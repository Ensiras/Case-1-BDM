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
        new RegistrerenGebruikerController(this).startRegistratie();
    }

    public void toonRegelement() {
        Regelement.toon(true);
    }
}
