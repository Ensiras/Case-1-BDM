package util;

import domain.Gebruiker;
import resources.GebruikerInput;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GebruikerInputMapper implements InputMapper<GebruikerInput, Gebruiker> {

    @Inject
    BezorgwijzenMapper bezorgwijzenMapper;

    @Override
    public Gebruiker mapFromInputToEntity(GebruikerInput gebruikerIn) {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setEmail(gebruikerIn.getEmail());

        gebruiker.setStraat(gebruikerIn.getStraat());
        gebruiker.setHuisnummer(gebruikerIn.getHuisnummer());
        gebruiker.setPostcode(gebruikerIn.getPostcode());
        gebruiker.setStad(gebruikerIn.getStad());

        gebruiker.setAkkoordVoorwaarden(gebruikerIn.isAkkoordVoorwaarden());
        gebruiker.setBezorgwijzen(bezorgwijzenMapper.mapBezorgwijzen(gebruikerIn));
        return gebruiker;
    }
}
