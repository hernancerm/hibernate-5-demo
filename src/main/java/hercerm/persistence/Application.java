package hercerm.persistence;

import hercerm.persistence.model.Director;
import hercerm.persistence.model.Genre;
import hercerm.persistence.model.Movie;
import hercerm.persistence.orm.EMFBootstrapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        EntityManager entityManager = EMFBootstrapper.open();

        // Crear entidades
        // ***************

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

        // Persistir entidades
        // *******************

        // Es necesario encerrar en una transacción aquellas sentencias
        // de DML que cambian el estado de datos.
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(akiraA);
        entityManager.persist(akiraB);
        entityManager.persist(lynchA);
        entityManager.persist(polanskiA);
        transaction.commit();

        // Ejecutar consultas con Java Persistence Query Language
        // ******************************************************
        // Ejemplo: https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html

        // Encontrar al director con primer nombre "Akira"
        String sqlSelectAKira =
                "SELECT d FROM Director d WHERE d.firstName = :firstName";
        Director akiraFetched = entityManager
                .createQuery(sqlSelectAKira,
                        Director.class)
                .setParameter("firstName", "Akira")
                .getSingleResult();

        // Encontrar todos los géneros
        List<Genre> genresFetched =  entityManager
                .createQuery("SELECT g FROM Genre g", Genre.class)
                .getResultList();

        // Imprimir resultados en salida estándar
        // **************************************

        System.out.printf("Director: %s has movies:\n", akiraFetched);
        for (Movie movie : akiraFetched.getMovies()) {
            System.out.println(movie);
        }

        System.out.println();

        for (Genre genre : genresFetched) {
            System.out.printf("Genre: %s has movies:\n", genre);
            for (Movie movie : genre.getMovies()) {
                System.out.println(movie);
            }
            System.out.println();
        }

        entityManager.close();
    }
}
