package service;

import dao.GebruikerDao;
import domain.Gebruiker;
import resources.GebruikerInput;
import util.GebruikerInputMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GebruikerService {

    @Inject
    GebruikerInputMapper gebruikerMapper;

    @Inject
    GebruikerDao dao;

    public GebruikerService() {
    }

    // TODO: als tijd over checken of email al bekend is.
/*    public boolean checkEmail(GebruikerInput gebruiker) {
        TypedQuery<GebruikerInput> query = em.createQuery("SELECT g FROM GebruikerInput g WHERE g.email = :email", GebruikerInput.class);
        query.setParameter("email", gebruiker.getEmail());
        List<GebruikerInput> resultList = query.getResultList();
        System.out.println(resultList.size());
        return true;
    }*/

    public Gebruiker registreerGebruiker(GebruikerInput gebruikerIn) {
        Gebruiker gebruiker = gebruikerMapper.mapGebruikerInputToGebruiker(gebruikerIn);
        dao.persist(gebruiker);
        return gebruiker;
    }

    public Gebruiker zoek(int id) {
        return dao.find(id, Gebruiker.class);
    }

}
