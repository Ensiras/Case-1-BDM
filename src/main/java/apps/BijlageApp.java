package apps;

import domain.Bijlage;

import javax.persistence.EntityManager;

import static util.DBUtil.getEntityManager;

public class BijlageApp {
    public static void main(String[] args) {
        Bijlage bijlage = new Bijlage();
        bijlage.setBijlage();
        EntityManager em = getEntityManager("MySQL");
        em.getTransaction().begin();
        em.persist(bijlage);
        em.getTransaction().commit();

    }
}
