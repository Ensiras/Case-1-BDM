package dao;

import domain.Gebruiker;

import javax.persistence.TypedQuery;
import java.util.List;

public class GebruikerDao extends AbstractDao<Gebruiker>{

    public GebruikerDao() {
        super();
    }

    public List<Gebruiker> find(String email) {
        TypedQuery<Gebruiker> query = em.createQuery("Select g from Gebruiker g where g.email = :email", Gebruiker.class);
        query.setParameter("email", email);
        return query.getResultList();
    }

}
