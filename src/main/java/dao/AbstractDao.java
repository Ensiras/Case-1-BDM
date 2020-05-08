package dao;

import util.EntityManagerWrapper;

import javax.persistence.EntityManager;

public abstract class AbstractDao<T> {

    protected EntityManager em;

    public AbstractDao(EntityManager em) {
        this.em = em;
    }

    public void opslaan(T toInsert) {
        em.getTransaction().begin();
        em.persist(toInsert);
        em.getTransaction().commit();
    }

    public T zoek(int id, Class<T> classType) {
        return em.find(classType, id);
    }

    public void sluitEntityManager() {
        EntityManagerWrapper.sluitEntityManager();
    }

}
