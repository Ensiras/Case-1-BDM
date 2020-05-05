package dao;


import domain.AbstractArtikel;

import javax.persistence.EntityManager;

public class ArtikelDao extends AbstractDao<AbstractArtikel> {


    public ArtikelDao(EntityManager em) {
        super(em);
    }

    @Override
    public void printAlles() {

    }
}
