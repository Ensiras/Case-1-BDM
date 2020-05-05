package dao;

import domain.Gebruiker;

import javax.persistence.EntityManager;

public class GebruikerDao extends AbstractDao<Gebruiker>{

    public GebruikerDao(EntityManager em) {
        super(em);
    }

    @Override
    public void printAlles() {
        System.out.println("Nog geen implementatie");
    }

}
