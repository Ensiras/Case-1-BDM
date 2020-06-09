package dao;


import domain.AbstractArtikel;

import javax.persistence.EntityManager;
import java.util.List;

public class ArtikelDao extends AbstractDao<AbstractArtikel> {

    public ArtikelDao(EntityManager em) {
        super(em);
    }

    public AbstractArtikel getById(int id) {
        return em.find(AbstractArtikel.class, id);
    }

}
