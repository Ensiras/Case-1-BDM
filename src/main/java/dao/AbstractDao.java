package dao;

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
    };

    // TODO: manier vinden om implementatie hetzelfde te hebben (dus niet abstract) maar return type wel variabel
    public T zoek(int id, Class<T> classType) {
        return em.find(classType, id);
    };

//    zoekalles
    // Zoek op naam

    public void sluitEntityManager() {
        em.close();
    }

    // TODO: meer methodes toevoegen die elke dao gaat gebruiken

}
