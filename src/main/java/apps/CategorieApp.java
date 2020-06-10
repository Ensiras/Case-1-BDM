package apps;

import domain.AbstractCategorie;
import domain.DienstCategorie;
import domain.ProductCategorie;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class CategorieApp {

    public static void main(String[] args) {
//        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        AbstractCategorie categorie1 = new ProductCategorie(
                "Postkoetswielen",
                "Postkoetswielen in alle soorten en maten.");
        AbstractCategorie categorie2 = new ProductCategorie(
                "Badkamertegels",
                "Tegels voor in de badkamer.");
        AbstractCategorie categorie3 = new ProductCategorie(
                "Ballenbakbenodigdheden",
                "Alles voor in en rond de ballenbak.");
        AbstractCategorie categorie4 = new ProductCategorie(
                "Spullen die ik nog ergens op zolder vond",
                "Alles wat niet binnen de overige categorieën valt");

        AbstractCategorie categorie5 = new DienstCategorie(
                "Iets vaags met computers",
                "Ja hij doet iets met IT, misschien weet hij het.");
        AbstractCategorie categorie6 = new DienstCategorie(
                "Matig uitgevoerd onderhoud rond het huis",
                "Alles betreft onderhoud rond het huis, echt vakmanschap niet toegestaan.");

        /*em.getTransaction().begin();
        em.persist(categorie1);
        em.persist(categorie2);
        em.persist(categorie3);
        em.persist(categorie4);
        em.persist(categorie5);
        em.persist(categorie6);

        em.getTransaction().commit();*/
    }
}
