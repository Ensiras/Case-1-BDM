package controller;

import dao.GebruikerDao;
import domain.Bezorgwijze;
import domain.Gebruiker;
import views.RegistrerenGebruikerView;

import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.AFHALEN_THUIS;
import static util.DBUtil.getEntityManager;
import static util.Regelement.getRegelementVoet;


// TODO: input-loops vervangen met vraagInput() methode
public class RegistrerenGebruikerController extends AbstractController<GebruikerDao, RegistrerenGebruikerView> {

    public RegistrerenGebruikerController() {
    }

    public RegistrerenGebruikerController(RegistrerenGebruikerView view) {
        this.view = view;
        this.dao = new GebruikerDao(getEntityManager("MySQL"));
        view.toonBericht("Registeren nieuwe gebruiker");
    }

    public boolean startRegistratie() {
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
            return false;
        }

        Gebruiker gebruiker = registreerGebruiker(email, bezorgwijzen, adres, true);
        view.toonBericht("Registratie van gebruiker " + gebruiker.getEmail() + " succesvol!");
        return true;
    }

    public Gebruiker registreerGebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String[] adres, boolean toestemming) {
        Gebruiker gebruiker = new Gebruiker(email, bezorgwijzen, adres, toestemming);
        dao.opslaan(gebruiker);
        view.sluitScanner();
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

    // TODO: misschien ook naar superklasse want hergebruikt (deels) in aanbieden artikel
    Set<Bezorgwijze> vraagBezorgwijzen() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        view.toonBericht("Ondersteunt u de volgende bezorgwijzen (j/n)?");
        String[] opties = {"j", "n"};

        // TODO: aparte methode in superklasse want hergebruikt in aanbieden artikel
        for (Bezorgwijze b : Bezorgwijze.values()) {
            boolean valideInput = false;
            String input = "";

            while (!valideInput) {
                input = view.vraagInput(b.getTypePrintbaar());
                valideInput = checkInput(input, opties);
            }

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

    // Zorgt ervoor dat alle velden ingevuld zijn en de postcode uit 4 getallen en 2 letters bestaat
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
