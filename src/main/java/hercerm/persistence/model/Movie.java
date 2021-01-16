package hercerm.persistence.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private long movieId;

    private String title;

    // CascadeType
    // ***********
    // La configuración de cascada define qué transiciones de
    // estado se pueden propagar a los hijos.
    //
    // En este caso, CascadeType.PERSIST indica que al persistir
    // una película, también se persiste al director.
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Director director;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private final Set<Genre> genres = new HashSet<>();

    public Movie() {}

    public Movie(String title, Director director) {
        this.title = title;
        director.addMovie(this);
        this.director = director;
    }

    public long getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void addGenre(Genre genre) {
        genre.addMovie(this);
        genres.add(genre);
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                '}';
    }
}
