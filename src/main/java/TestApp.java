import dao.GebruikerDao;
import domain.Bezorgwijze;
import domain.Gebruiker;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.VERSTUREN_REMBOURS;
import static domain.Bezorgwijze.VERSTUREN_VOORBET;

public class TestApp {


    public static void main(String[] args) {

        new TestApp().start();


    }

    private void start() {
        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        GebruikerDao dao = new GebruikerDao(em);

        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();
        bezorgwijzeSet.add(VERSTUREN_VOORBET);
        bezorgwijzeSet.add(VERSTUREN_REMBOURS);

        Gebruiker gebruiker = new Gebruiker("test@example.com", bezorgwijzeSet, "Geen adres, helaas");

        dao.insert(gebruiker);
    }
}
