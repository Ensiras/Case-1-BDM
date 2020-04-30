package controller;

import domain.Bezorgwijze;
import domain.Gebruiker;
import views.RegGebruikerView;

import java.util.Set;

import static domain.Bezorgwijze.AFHALEN_THUIS;

public class RegistrerenGebruiker {

    private RegistrerenGebruiker() {};

    public static Gebruiker registreren(String email, Set<Bezorgwijze> bezorgwijzen) {
        checkEmail(email);
        String adres = bepaalAdres(bezorgwijzen);
        if (adres != null) {
            checkAdres(adres);
        }
        return new Gebruiker(email, bezorgwijzen, adres);
    }

    private static String bepaalAdres(Set<Bezorgwijze> bezorgwijzen) {
        String adres;
        if (bezorgwijzen.contains(AFHALEN_THUIS)) {
            adres = RegGebruikerView.vraagAdres();
        } else {
            adres = null;
        }
        return adres;
    }

    private static void checkAdres(String adres) {
        /*String[] adresSplit = adres.split("/");
        adresSplit[1].matches("\\p{L}");
        adresSplit[2].matches("[0-9]+")*/
    }

    private static void checkEmail(String email) {
        System.out.println("Checken van email is nog niet geïmplementeerd.");
    }


}
