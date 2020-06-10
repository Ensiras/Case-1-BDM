package dao;

import domain.AbstractCategorie;
import domain.ArtikelSoort;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static domain.ArtikelSoort.PRODUCT;

public class CategorieDao extends AbstractDao<AbstractCategorie> {

    public CategorieDao() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List<AbstractCategorie> zoekAlles(ArtikelSoort artikelSoort) {
        Query query;
        if (artikelSoort.equals(PRODUCT)) {
             query = em.createQuery("SELECT P FROM ProductCategorie P");
        } else {
            query = em.createQuery("SELECT D FROM DienstCategorie D");
        }
        return query.getResultList();
    }


}
