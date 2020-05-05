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
                "Skippyballen",
                "Skippyballen is alle soorten en maten.");
        AbstractCategorie categorie2 = new DienstCategorie(
                "Kunst",
                "Schilder, tekenaar of beeldhouwer nodig? Kijk hier!");
        em.getTransaction().begin();
        em.persist(categorie1);
        em.persist(categorie2);
        em.getTransaction().commit();
    }
}
