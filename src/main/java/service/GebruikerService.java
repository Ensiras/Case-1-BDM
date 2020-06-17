package service;

import dao.GebruikerDao;
import domain.Gebruiker;
import resources.GebruikerInput;
import util.InputMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GebruikerService {

    @Inject
    InputMapper<GebruikerInput, Gebruiker> gebruikerMapper;

    @Inject
    GebruikerDao dao;

    public GebruikerService() {
    }

    public Gebruiker registreerGebruiker(GebruikerInput gebruikerIn) {
        Gebruiker gebruiker = gebruikerMapper.mapFromInputToEntity(gebruikerIn);
        dao.persist(gebruiker);
        return gebruiker;
    }

    public Gebruiker zoek(int id) {
        return dao.find(id, Gebruiker.class);
    }

}
