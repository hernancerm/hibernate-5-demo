package hercerm.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Director {
    @Id
    @GeneratedValue
    private long directorId;

    private String firstName;
    private String lastName;

    // mappedBy (para asociaciones bidireccionales OneToMany)
    // ******************************************************
    // mappedBy indica qué lado de la asociación se encarga
    // de realizar el mapeo.
    //
    // En una asociación "uno a muchos", el lado de "muchos"
    // debe de realizar el mapeo.
    @OneToMany(mappedBy = "director")
    private final Set<Movie> movies = new HashSet<>();

    public Director() {}

    public Director(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addMovie(Movie movie) {
        movie.setDirector(this);
        movies.add(movie);
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorId=" + directorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
