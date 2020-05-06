package apps;

import domain.*;

import javax.persistence.EntityManager;

import java.math.BigDecimal;

import static util.DBUtil.getEntityManager;

public class ArtikelApp {
    public static void main(String[] args) {
/*        EntityManager em = getEntityManager();
        Gebruiker gebruiker = em.find(Gebruiker.class, 1);
        *//*AbstractArtikel abstractArtikel = new Product(gebruiker, "Bowling bal",
                BigDecimal.valueOf(34.54), new ProductCategorie("Bowlingdingen", "Alles voor bowling"));*//*
        em.getTransaction().begin();
        em.persist(abstractArtikel);
        em.getTransaction().commit();*/
    }
}
