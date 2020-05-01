package util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DBUtil {

    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("MySQL").createEntityManager();
    }
}
