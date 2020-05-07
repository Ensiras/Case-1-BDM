package dao;

import domain.Gebruiker;

import javax.persistence.EntityManager;

public class GebruikerDao extends AbstractDao<Gebruiker>{

    public GebruikerDao(EntityManager em) {
        super(em);
    }

}
