package service;

import domain.Adres;
import domain.Bezorgwijze;
import domain.Gebruiker;
import resources.GebruikerInput;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

import static domain.Bezorgwijze.*;

@Stateless
public class RegistrerenGebruikerService {
    @PersistenceContext
    EntityManager em;

    // TODO: als tijd over checken of email al bekend is.
/*    public boolean checkEmail(GebruikerInput gebruiker) {
        TypedQuery<GebruikerInput> query = em.createQuery("SELECT g FROM GebruikerInput g WHERE g.email = :email", GebruikerInput.class);
        query.setParameter("email", gebruiker.getEmail());
        List<GebruikerInput> resultList = query.getResultList();
        System.out.println(resultList.size());
        return true;
    }*/

    public Gebruiker registreerGebruiker(GebruikerInput gebruikerIn) {
        return mapGebruikerInToGebruiker(gebruikerIn);
    }

    // TODO: in aparte gebruikermapper zetten
    // Mappen ID niet nodig (denk ik) omdat deze mapping alleen plaastvind bij het registreren van een nieuwe gebruiker
    // Nieuwe methode is nodig voor aanpassen gebruiker.
    private Gebruiker mapGebruikerInToGebruiker(GebruikerInput gebruikerIn) {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setEmail(gebruikerIn.getEmail());
        gebruiker.setBezorgwijzen(mapBezorgwijzen(gebruikerIn));
        gebruiker.setAdres(mapAdres(gebruikerIn));
        gebruiker.setAkkoordVoorwaarden(gebruikerIn.isAkkoordVoorwaarden());
        return gebruiker;
    }

    // TODO: mapper naar andere kant aanmaken.

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
