package util;

import domain.Adres;
import domain.Bezorgwijze;
import domain.Gebruiker;
import resources.GebruikerInput;

import javax.ejb.Stateless;
import java.util.LinkedHashSet;
import java.util.Set;

import static domain.Bezorgwijze.*;
import static domain.Bezorgwijze.VERSTUREN_REMBOURS;

@Stateless
public class GebruikerInputMapper {

    public Gebruiker mapGebruikerInputToGebruiker(GebruikerInput gebruikerIn) {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setEmail(gebruikerIn.getEmail());
        gebruiker.setBezorgwijzen(mapBezorgwijzen(gebruikerIn));
        gebruiker.setAdres(mapAdres(gebruikerIn));
        gebruiker.setAkkoordVoorwaarden(gebruikerIn.isAkkoordVoorwaarden());
        return gebruiker;
    }

    private Adres mapAdres(GebruikerInput gebruikerIn) {
        return new Adres(gebruikerIn.getStraat(),
                gebruikerIn.getHuisnummer(),
                gebruikerIn.getPostcode(),
                gebruikerIn.getStad());
    }

    private Set<Bezorgwijze> mapBezorgwijzen(GebruikerInput gebruikerIn) {
        Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();

        if (gebruikerIn.isBezorgAfhalenMagazijn()) {
            bezorgwijzen.add(AFHALEN_MAGAZIJN);
        }
        if (gebruikerIn.isBezorgAfhalenThuis()) {
            bezorgwijzen.add(AFHALEN_THUIS);
        }
        if (gebruikerIn.isBezorgVersturenVooruit()) {
            bezorgwijzen.add(VERSTUREN_VOORBET);
        }
        if (gebruikerIn.isBezorgVersturenRembours()) {
            bezorgwijzen.add(VERSTUREN_REMBOURS);
        }

        return bezorgwijzen;
    }
}
