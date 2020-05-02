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

public class RegistrerenGebruiker {

    private final RegGebruikerView view;

    public RegistrerenGebruiker(RegGebruikerView view) {
        this.view = view;
    }

    public Gebruiker registreerGebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String[] adres, boolean toestemming) {
        GebruikerDao dao = new GebruikerDao(getEntityManager());
        Gebruiker gebruiker = new Gebruiker(email, bezorgwijzen, adres, toestemming);
        dao.insert(gebruiker);
        return gebruiker;
    }

    public void startRegistratie() {
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
            view.toonBericht("Registratie van gebruiker " + gebruiker.getEmail() + " succesvol!");
        } else {
            view.toonBericht("Registratie wordt afgebroken.");
        }
    }

    private boolean vraagToestemming() {
        boolean valideInput = false;
        String[] opties = {"j", "n"};
        String input = "";

        view.toonRegelement();
        while(!valideInput) {
            input = view.vraagInput(getRegelementVoet());
            valideInput = checkInput(input, opties);
        }
        return input.equals("j");
    }

    private String vraagEmail() {
        boolean emailCheck = false;
        String email = "";
        while (!emailCheck) {
            email = view.vraagInput("Voer uw e-mailadres in");
            emailCheck = checkEmail(email);
        }
        return email;
    }

    private Set<Bezorgwijze> vraagBezorgwijzen() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        view.toonBericht("Ondersteunt u de volgende bezorgwijzen (j/n)?");
        String[] opties = {"j", "n"};

        for (Bezorgwijze b : Bezorgwijze.values()) {
            boolean valideInput = false;
            String input = "";
            while (!valideInput) {
                input = view.vraagInput(b.toString());
                valideInput = checkInput(input, opties);
            }

            if (input.equals("j")) {
                bezorgwijzen.add(b);
            }
        }
        return bezorgwijzen;
    }

    // TODO naar (abstracte) superklasse
    private boolean checkInput(String input, String[] opties) {
        for (String optie : opties) {
            if (optie.equals(input)) {
                return true;
            }
        }
        view.toonBericht("Input: " + input + " werd niet herkend.");
        return false;
    }

    private String[] vraagAdres() {
        String[] adres = new String[3];
        boolean adresCheck = false;

        while (!adresCheck) {
            view.toonBericht("Voer uw adres in.");
            adres[0] = view.vraagInput("Straat");
            adres[1] = view.vraagInput("Huisnummer");
            adres[2] = view.vraagInput("Postcode");
            adresCheck = checkAdres(adres);
        }
        return adres;
    }

    public boolean checkAdres(String[] adres) {
        String straat = adres[0];
        String huisnummer = adres[1];
        String postcode = adres[2];

        if (straat.isEmpty() || huisnummer.isEmpty() || postcode.isEmpty()) {
            view.toonBericht("Niet alle velden ingevuld.");
            return false;
        } else if (!postcode.matches("^[0-9]{4}[a-zA-Z]{2}")) {
            view.toonBericht("Ongeldige postcode opgegeven.");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            return true;
        }
        System.out.println("Dit is geen geldig e-mailadres");
        return false;
    }

    public boolean checkBezorgwijzen(Set<Bezorgwijze> bezorgwijzen) {
        return bezorgwijzen.contains(AFHALEN_THUIS);
    }

}
