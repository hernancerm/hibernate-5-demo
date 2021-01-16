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

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Director director;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Genre> genres = new HashSet<>();

    public Movie() {}

    public Movie(String title, Director director) {
        this.title = title;
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

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public Set<Genre> getGenres() {
        return genres;
    }
}
