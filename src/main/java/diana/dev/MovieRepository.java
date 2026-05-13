package diana.dev;

import java.util.List;

public interface MovieRepository {
    public void addMovie(Movie movie);
    public List<Movie> getAllMovies();
    public Movie findByGenre(String genre);
    public void updateTitle(Long id, String newTitle);
    public void deleteMovie(Long id);
}
