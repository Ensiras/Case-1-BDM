import controller.RegistrerenGebruiker;
import domain.Bezorgwijze;
import domain.Gebruiker;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.*;

public class TestApp {

    public static void main(String[] args) {
        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();
        bezorgwijzeSet.add(VERSTUREN_VOORBET);
        bezorgwijzeSet.add(VERSTUREN_REMBOURS);

        Gebruiker gebruiker = new Gebruiker("test@example.com", bezorgwijzeSet, "Geen adres, helaas");

        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        em.getTransaction().begin();
        em.persist(gebruiker);
        em.getTransaction().commit();


    }
}
