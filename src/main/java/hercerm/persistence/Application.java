package hercerm.persistence;

import hercerm.persistence.model.Director;
import hercerm.persistence.model.Genre;
import hercerm.persistence.model.Movie;
import hercerm.persistence.orm.EMFBootstrapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Application {

    public static void main(String[] args) {
        EntityManager entityManager = EMFBootstrapper.open();

        // Directores
        Director akira = new Director("Akira", "Kurosawa");
        Director lynch = new Director("David", "Lynch");
        Director polanski = new Director("Roman", "Polanski");


        // Géneros
        Genre horror = new Genre("Horror");
        Genre thriller = new Genre("Thriller");


        // Películas
        Movie akiraA = new Movie("Akira A", akira);
        akiraA.addGenre(horror);

        Movie akiraB = new Movie("Akira B", akira);
        akiraB.addGenre(thriller);

        Movie lynchA = new Movie("Lynch A", lynch);
        lynchA.addGenre(horror);
        lynchA.addGenre(thriller);

        Movie polanskiA = new Movie("Polanski A", polanski);
        polanskiA.addGenre(horror);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(akiraA);
        entityManager.persist(akiraB);
        entityManager.persist(lynchA);
        entityManager.persist(polanskiA);
        transaction.commit();
    }
}
