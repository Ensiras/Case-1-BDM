package dao;

import domain.Bezorgwijze;
import domain.Gebruiker;

import javax.persistence.EntityManager;
import java.util.HashSet;

public class GebruikerDao extends AbstractDao<Gebruiker>{

    public GebruikerDao(EntityManager em) {
        super(em);
    }


}
