package dao;

import util.EntityManagerWrapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T> {

    @PersistenceContext
    protected EntityManager em;

    public AbstractDao() {

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
