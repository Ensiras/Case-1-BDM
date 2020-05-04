package apps;

import domain.ArtikelSoort;
import domain.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static domain.ArtikelSoort.DIENST;
import static domain.ArtikelSoort.PRODUCT;

public class CategorieApp {

    public static void main(String[] args) {
/*        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        Categorie categorie1 = new Categorie(
                "Bowlingbenodigdheden", PRODUCT,
                "Alle producten voor het uitvoeren van de sport 'bowling'.");
        Categorie categorie2 = new Categorie(
                "Muziek", DIENST,
                "Muzikant of band nodig? Kijk hier!");
        em.getTransaction().begin();
        em.persist(categorie1);
        em.persist(categorie2);
        em.getTransaction().commit();*/
    }
}
