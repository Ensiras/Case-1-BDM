package controller;

import dao.GebruikerDao;
import domain.Bezorgwijze;
import domain.Gebruiker;

import java.util.Set;

import static domain.Bezorgwijze.AFHALEN_THUIS;
import static util.DBUtil.getEntityManager;

// TODO: niet static maken?
public class RegistrerenGebruiker {

    private static GebruikerDao dao = new GebruikerDao(getEntityManager());

    private RegistrerenGebruiker() {
    }

    public static Gebruiker registreerGebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String[] adres) {
        Gebruiker gebruiker = new Gebruiker(email, bezorgwijzen, adres);
        dao.insert(gebruiker);
        return gebruiker;
    }

    public static Gebruiker registreerGebruiker(String email, Set<Bezorgwijze> bezorgwijzen) {
        return new Gebruiker(email, bezorgwijzen);
    }

    public static boolean checkAdres(String[] adres) {
        String straat = adres[0];
        String huisnummer = adres[1];
        String postcode = adres[2];

        if (straat.isEmpty() || huisnummer.isEmpty() || postcode.isEmpty()) {
            System.out.println("Niet alle velden ingevuld.");
            return false;
        } else if (!postcode.matches("^[0-9]{4}[a-zA-Z]{2}")) {
            System.out.println("Ongeldige postcode opgegeven.");
            return false;
        } else {
            return true;
        }
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
