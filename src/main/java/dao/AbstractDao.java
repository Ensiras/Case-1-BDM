package dao;

import util.EntityManagerWrapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T> {

    @PersistenceContext
    protected EntityManager em;

    public AbstractDao() {

    }

    public void persist(T toInsert) {
        em.persist(toInsert);
    }

    public T find(int id, Class<T> classType) {
        return em.find(classType, id);
    }



    public void sluitEntityManager() {
        EntityManagerWrapper.sluitEntityManager();
    }

}
