package apps;

import domain.AbstractCategorie;
import domain.DienstCategorie;
import domain.ProductCategorie;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class CategorieApp {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        AbstractCategorie categorie1 = new ProductCategorie(
                "Sport",
                "Alles wat met sport te maken heeft.");
        AbstractCategorie categorie2 = new ProductCategorie(
                "Hobby",
                "Spullen voor allerlei hobby's.");
        AbstractCategorie categorie3 = new DienstCategorie(
                "Kunst",
                "Schilder, tekenaar of beeldhouwer nodig? Kijk hier!");
        em.getTransaction().begin();
        em.persist(categorie1);
        em.persist(categorie2);
        em.persist(categorie3);
        em.getTransaction().commit();
    }
}
