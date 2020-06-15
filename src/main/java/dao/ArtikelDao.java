package dao;


import domain.AbstractArtikel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;
@Stateless
public class ArtikelDao extends AbstractDao<AbstractArtikel> {

    public ArtikelDao() {
        super();
    }

    public AbstractArtikel getById(int id) {
        return em.find(AbstractArtikel.class, id);
    }

}
