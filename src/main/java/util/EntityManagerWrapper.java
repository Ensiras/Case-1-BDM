package util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerWrapper {

    private static EntityManager instance;

    private EntityManagerWrapper() {}

    public static EntityManager getEntityManager(String type) {
        if (instance != null) {
            return instance;
        }
        instance = Persistence.createEntityManagerFactory(type).createEntityManager();
        return instance;
    }

    public static void sluitEntityManager() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }

}
