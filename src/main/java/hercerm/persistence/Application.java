package hercerm.persistence;

import hercerm.persistence.model.Movie;
import hercerm.persistence.orm.EMFBootstrapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Application {

    public static void main(String[] args) {
        EntityManager entityManager = EMFBootstrapper.open();

        Movie movie = new Movie("No he visto una película en meses.", "Yo");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(movie);
        transaction.commit();
    }
}
