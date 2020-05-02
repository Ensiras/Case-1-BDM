package controller;

import dao.GebruikerDao;
import domain.Bezorgwijze;
import domain.Gebruiker;
import views.RegGebruikerView;

import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.AFHALEN_THUIS;
import static util.DBUtil.getEntityManager;
import static util.Regelement.getRegelementVoet;


// TODO: niet static maken?
public class RegistrerenGebruiker {

    private static GebruikerDao dao;

    private RegistrerenGebruiker() {
    }

    public static Gebruiker registreerGebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String[] adres, boolean toestemming) {
        dao = new GebruikerDao(getEntityManager());
        Gebruiker gebruiker = new Gebruiker(email, bezorgwijzen, adres, toestemming);
        dao.insert(gebruiker);
        return gebruiker;
    }

    public static void startRegistratie() {
        String[] adres = new String[3];

        String email = vraagEmail();
        Set<Bezorgwijze> bezorgwijzen = vraagBezorgwijzen();

        // Als thuis afhalen wordt ondersteund vraag dan het adres:
        if (checkBezorgwijzen(bezorgwijzen)) {
            adres = vraagAdres();
        }
        // Als toestemming gegeven wordt, registreer dan een gebruiker
        if(vraagToestemming()) {
            Gebruiker gebruiker = registreerGebruiker(email, bezorgwijzen, adres, true);
            RegGebruikerView.toonBericht("Registratie van gebruiker " + gebruiker.getEmail() + " succesvol!");
        } else {
            RegGebruikerView.toonBericht("Registratie wordt afgebroken.");
        }
    }

    private static boolean vraagToestemming() {
        boolean valideInput = false;
        String[] opties = {"j", "n"};
        String input = "";

        RegGebruikerView.toonRegelement();
        while(!valideInput) {
            input = RegGebruikerView.vraagInput(getRegelementVoet());
            valideInput = checkInput(input, opties);
        }
        return input.equals("j");
    }

    private static String vraagEmail() {
        boolean emailCheck = false;
        String email = "";
        while (!emailCheck) {
            email = RegGebruikerView.vraagInput("Voer uw e-mailadres in");
            emailCheck = checkEmail(email);
        }
        return email;
    }

    private static Set<Bezorgwijze> vraagBezorgwijzen() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        RegGebruikerView.toonBericht("Ondersteunt u de volgende bezorgwijzen (j/n)?");
        String[] opties = {"j", "n"};

        for (Bezorgwijze b : Bezorgwijze.values()) {
            boolean valideInput = false;
            String input = "";
            while (!valideInput) {
                input = RegGebruikerView.vraagInput(b.toString());
                valideInput = checkInput(input, opties);
            }

            if (input.equals("j")) {
                bezorgwijzen.add(b);
            }
        }
        return bezorgwijzen;
    }

    // TODO naar (abstracte) superklasse
    private static boolean checkInput(String input, String[] opties) {
        for (String optie : opties) {
            if (optie.equals(input)) {
                return true;
            }
        }
        RegGebruikerView.toonBericht("Input: " + input + " werd niet herkend.");
        return false;
    }

    private static String[] vraagAdres() {
        String[] adres = new String[3];
        boolean adresCheck = false;

        while (!adresCheck) {
            RegGebruikerView.toonBericht("Voer uw adres in.");
            adres[0] = RegGebruikerView.vraagInput("Straat");
            adres[1] = RegGebruikerView.vraagInput("Huisnummer");
            adres[2] = RegGebruikerView.vraagInput("Postcode");
            adresCheck = checkAdres(adres);
        }
        return adres;
    }

    public static boolean checkAdres(String[] adres) {
        String straat = adres[0];
        String huisnummer = adres[1];
        String postcode = adres[2];

        if (straat.isEmpty() || huisnummer.isEmpty() || postcode.isEmpty()) {
            RegGebruikerView.toonBericht("Niet alle velden ingevuld.");
            return false;
        } else if (!postcode.matches("^[0-9]{4}[a-zA-Z]{2}")) {
            RegGebruikerView.toonBericht("Ongeldige postcode opgegeven.");
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
