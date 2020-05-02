package views;

import controller.RegistrerenGebruiker;

import util.Regelement;
import java.util.Scanner;

public class RegGebruikerView {

    private static Scanner scanner;

    public static void show() {
        scanner = new Scanner(System.in);
        registreerGebruikerNieuw();
    }

    private RegGebruikerView() {
    }

    public static void registreerGebruikerNieuw() {
        RegistrerenGebruiker.startRegistratie();
    }

    public static String vraagInput(String bericht) {
        System.out.println(bericht);
        return scanner.nextLine().trim().toLowerCase();
    }

    public static void toonBericht(String bericht) {
        System.out.println(bericht);
    }

    public static void toonRegelement() {
        Regelement.toon(true);
    }
}
