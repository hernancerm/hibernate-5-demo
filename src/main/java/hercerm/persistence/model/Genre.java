package hercerm.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue
    private long genreId;

    private String name;

    // mappedBy (para asociaciones bidireccionales ManyToMany)
    // *******************************************************
    // mappedBy indica qué lado de la asociación se encarga
    // de realizar el mapeo.
    //
    // En una asociación de "muchos a muchos", es necesario crear
    // una nueva tabla para representar el mapeo. En Hibernate,
    // es necesario indicar qué lado dirige la asociación y esto
    // puede decidirse según lo que conceptualmente tenga más sentido.
    //
    // En este caso, tiene más sentido pensar que:
    //     "Las películas tienen géneros"
    // En lugar de:
    //     "Los géneros tienen películas"
    // Por lo tanto, la responsabilidad de mapeo es de las películas.
    @ManyToMany(mappedBy = "genres")
    private final Set<Movie> movies = new HashSet<>();

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", name='" + name + '\'' +
                '}';
    }
}
