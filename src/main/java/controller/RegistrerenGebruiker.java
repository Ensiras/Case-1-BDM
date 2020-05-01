package controller;

import domain.Bezorgwijze;
import domain.Gebruiker;
import views.RegGebruikerView;

import java.util.Set;

import static domain.Bezorgwijze.AFHALEN_THUIS;

public class RegistrerenGebruiker {

    private RegistrerenGebruiker() {
    }

    public static Gebruiker registreerGebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String adres) {
        return new Gebruiker(email, bezorgwijzen, adres);
    }

    public static Gebruiker registreerGebruiker(String email, Set<Bezorgwijze> bezorgwijzen) {
        return new Gebruiker(email, bezorgwijzen, null);
    }

    // TODO valideren adres inbouwen
    public static boolean checkAdres(String adres) {
        System.out.println("Checken van adres is nog niet geïmplementeerd.");
        /*String[] adresSplit = adres.split("/");
        adresSplit[1].matches("\\p{L}");
        adresSplit[2].matches("[0-9]+")*/
        return true;
    }

    public static boolean checkEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            return true;
        }
        System.out.println("Dit is geen geldig e-mailadres");
        return false;
    }

    public static boolean checkBezorgwijzen(Set<Bezorgwijze> bezorgwijzen) {
        return bezorgwijzen.contains(AFHALEN_THUIS);
    }
}
