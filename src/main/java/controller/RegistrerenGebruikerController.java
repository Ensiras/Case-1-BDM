package controller;

import dao.GebruikerDao;
import domain.Bezorgwijze;
import domain.Gebruiker;
import views.RegistrerenGebruikerView;

import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.AFHALEN_THUIS;
import static domain.Regelement.getRegelementVoet;
import static util.DBUtil.getEntityManager;


public class RegistrerenGebruikerController extends AbstractController<GebruikerDao, RegistrerenGebruikerView> {

    public RegistrerenGebruikerController() {
    }

    public RegistrerenGebruikerController(RegistrerenGebruikerView view) {
        this.view = view;
        view.toonBericht("Registeren nieuwe gebruiker");
    }

    public void startRegistratie() {
        String[] adres = new String[3];

        String email = vraagEmail();
        Set<Bezorgwijze> bezorgwijzen = vraagBezorgwijzen();

        // Als thuis afhalen wordt ondersteund vraag dan het adres
        if (checkBezorgwijzen(bezorgwijzen)) {
            adres = vraagAdres();
        }

        // Als toestemming niet gegeven wordt, breek registratie af
        if (!vraagToestemming()) {
            view.toonBericht("Registratie wordt afgebroken.");
        } else {
            Gebruiker gebruiker = registreerGebruiker(email, bezorgwijzen, adres, true);
            view.toonBericht("Registratie van gebruiker " + gebruiker.getEmail() + " succesvol!");
        }
    }

    public Gebruiker registreerGebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String[] adres, boolean toestemming) {
        Gebruiker gebruiker = new Gebruiker(email, bezorgwijzen, adres, toestemming);
        GebruikerDao dao = new GebruikerDao(getEntityManager("MySQL"));
        dao.opslaan(gebruiker);
        dao.sluitEntityManager();
        return gebruiker;
    }

    boolean vraagToestemming() {
        String[] opties = {"j", "n"};
        view.toonRegelement();

        String input = vraagInput(opties, getRegelementVoet());
        return input.equals("j");
    }

    String vraagEmail() {
        boolean emailCheck = false;
        String email = "";
        while (!emailCheck) {
            email = view.vraagInput("Voer uw e-mailadres in");
            emailCheck = checkEmail(email);
        }
        return email;
    }

    boolean checkEmail(String email) {
        if (email.contains("@")) {
            String postfix = email.substring(email.indexOf("@"));
            if (postfix.contains(".")) {
                return true;
            }
        }
        System.out.println("Dit is geen geldig e-mailadres");
        return false;
    }

    Set<Bezorgwijze> vraagBezorgwijzen() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        view.toonBericht("Ondersteunt u de volgende bezorgwijzen (j/n)?");
        String[] opties = {"j", "n"};

        for (Bezorgwijze b : Bezorgwijze.values()) {
            boolean valideInput = false;
            String input = "";

            input = vraagInput(opties, b.getTypePrintbaar());
            if (input.equals("j")) {
                bezorgwijzen.add(b);
            }
        }
        return bezorgwijzen;
    }

    public boolean checkBezorgwijzen(Set<Bezorgwijze> bezorgwijzen) {
        return bezorgwijzen.contains(AFHALEN_THUIS);
    }

    String[] vraagAdres() {
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

    boolean checkAdres(String[] adres) {
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

}
