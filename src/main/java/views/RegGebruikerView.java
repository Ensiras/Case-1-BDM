package views;

import controller.RegistrerenGebruikerController;

import util.Regelement;
import java.util.Scanner;

public class RegGebruikerView {

    private final Scanner scanner;

    public void show() {
        registreerGebruiker();
    }

    public RegGebruikerView() {
        this.scanner = new Scanner(System.in);
    }

    public void registreerGebruiker() {
        new RegistrerenGebruikerController(this).startRegistratie();
    }

    public String vraagInput(String bericht) {
        System.out.println(bericht);
        return scanner.nextLine().trim().toLowerCase();
    }

    public void toonBericht(String bericht) {
        System.out.println(bericht);
    }

    public void toonRegelement() {
        Regelement.toon(true);
    }
}
