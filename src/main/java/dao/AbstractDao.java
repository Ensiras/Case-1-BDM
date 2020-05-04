package dao;

import javax.persistence.EntityManager;

public abstract class AbstractDao<T> {

    protected EntityManager em;

    public AbstractDao(EntityManager em) {
        this.em = em;
    }

    // Todo: Nederlandse naam??
    public abstract void insert(T toInsert);

    // TODO: manier vinden om implementatie hetzelfde te hebben (dus niet abstract) maar return type wel variabel
    public abstract T zoek(int id);

    // TODO: meer methodes toevoegen die elke dao gaat gebruiken

}
