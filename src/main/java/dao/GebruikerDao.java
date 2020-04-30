package dao;

import domain.Gebruiker;

import javax.persistence.EntityManager;

public class GebruikerDao {

    private EntityManager em;

    public GebruikerDao(EntityManager em) {
        this.em = em;
    }

    public void insert(Gebruiker gebruiker) {
        em.getTransaction().begin();
        em.persist(gebruiker);
        em.getTransaction().commit();
    }

}
