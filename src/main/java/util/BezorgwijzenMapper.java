package util;

import domain.Bezorgwijze;
import resources.ArtikelInput;
import resources.GebruikerInput;

import javax.ejb.Stateless;
import java.util.LinkedHashSet;
import java.util.Set;

import static domain.Bezorgwijze.*;

@Stateless
public class BezorgwijzenMapper {


    public Set<Bezorgwijze> mapBezorgwijzen(ArtikelInput artikelInput) {
        Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();

        if (artikelInput.isBezorgAfhalenMagazijn()) {
            bezorgwijzen.add(AFHALEN_MAGAZIJN);
        }
        if (artikelInput.isBezorgAfhalenThuis()) {
            bezorgwijzen.add(AFHALEN_THUIS);
        }
        if (artikelInput.isBezorgVersturenVooruit()) {
            bezorgwijzen.add(VERSTUREN_VOORBET);
        }
        if (artikelInput.isBezorgVersturenRembours()) {
            bezorgwijzen.add(VERSTUREN_REMBOURS);
        }

        return bezorgwijzen;
    }


    public Set<Bezorgwijze> mapBezorgwijzen(GebruikerInput gebruikerIn) {
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
