package dao;

import domain.Gebruiker;

import javax.persistence.EntityManager;

public class GebruikerDao extends AbstractDao<Gebruiker>{

    public GebruikerDao(EntityManager em) {
        super(em);
    }

    public void insert(Gebruiker gebruiker) {
        em.getTransaction().begin();
        em.persist(gebruiker);
        em.getTransaction().commit();
    }

    @Override
    public Gebruiker zoek(int id) {
        return null;
    }

}
