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
        return getBezorgwijzen(
                artikelInput.isBezorgAfhalenMagazijn(), artikelInput.isBezorgAfhalenThuis(),
                artikelInput.isBezorgVersturenVooruit(), artikelInput.isBezorgVersturenRembours());
    }


    public Set<Bezorgwijze> mapBezorgwijzen(GebruikerInput gebruikerIn) {
        return getBezorgwijzen(
                gebruikerIn.isBezorgAfhalenMagazijn(), gebruikerIn.isBezorgAfhalenThuis(),
                gebruikerIn.isBezorgVersturenVooruit(), gebruikerIn.isBezorgVersturenRembours());
    }

    Set<Bezorgwijze> getBezorgwijzen(boolean bezorgAfhalenMagazijn, boolean bezorgAfhalenThuis,
                                     boolean bezorgVersturenVooruit, boolean bezorgVersturenRembours) {
        Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();

        if (bezorgAfhalenMagazijn) {
            bezorgwijzen.add(AFHALEN_MAGAZIJN);
        }
        if (bezorgAfhalenThuis) {
            bezorgwijzen.add(AFHALEN_THUIS);
        }
        if (bezorgVersturenVooruit) {
            bezorgwijzen.add(VERSTUREN_VOORBET);
        }
        if (bezorgVersturenRembours) {
            bezorgwijzen.add(VERSTUREN_REMBOURS);
        }

        return bezorgwijzen;
    }

}
