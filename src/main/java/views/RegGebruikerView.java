package views;

import controller.RegistrerenGebruiker;
import domain.Bezorgwijze;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RegGebruikerView {

    private static Scanner scanner;

    public static void show() {
        scanner = new Scanner(System.in);
        registreer();
    }

    private RegGebruikerView() { }

    public static void registreer() {
        String email = vraagEmail();
        Set<Bezorgwijze> ondersteundeBw = vraagBezorgwijzen();
        RegistrerenGebruiker.registreren(email, ondersteundeBw);
    }

    private static String vraagEmail() {
        System.out.println("Geef e-mailadres op: ");
        return scanner.nextLine();
    }

    private static Set<Bezorgwijze> vraagBezorgwijzen() {
        System.out.println("Ondersteunt u de volgende bezorgwijzen? (j/n)");
        Set<Bezorgwijze> onderSteundeBw = new HashSet<>();

        for(Bezorgwijze bw : Bezorgwijze.values()) {
            System.out.println(bw);
            String ondersteunt = scanner.nextLine().toLowerCase();
            if(ondersteunt.equals("j")) {
                onderSteundeBw.add(bw);
            } else if (!ondersteunt.equals("n")) {
                throw new RuntimeException("Je hebt geen j/J of n1/N ingetypt!");
            }
        }
        return onderSteundeBw;
    }

    public static String vraagAdres() {
        scanner = new Scanner(System.in);
        String adres;
        System.out.println("Voer uw adres in");

        System.out.println("Straat: ");
        adres = scanner.nextLine();

        System.out.println("/Huisnummer: ");
        adres += " " + scanner.nextLine();

        System.out.println("/Postcode: ");
        adres += " " + scanner.nextLine();

        return adres;
    }

}
