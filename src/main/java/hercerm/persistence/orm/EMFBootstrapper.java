package hercerm.persistence.orm;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFBootstrapper {
    // Se debe tener un sólo EntityManagerFactory por unidad de persistencia.
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("defaultPersistenceUnit");

    public static EntityManager open() {
        return emf.createEntityManager();
    }
}
