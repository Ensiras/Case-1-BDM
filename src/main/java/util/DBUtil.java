package util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DBUtil {

    // TODO: in aparte singleton klasse
    private static EntityManager instance;

    public static EntityManager getEntityManager(String type) {
        if (instance != null) {
            return instance;
        }
        instance = Persistence.createEntityManagerFactory(type).createEntityManager();
        return instance;
    }

    public static void closeEntityManager() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }

}
