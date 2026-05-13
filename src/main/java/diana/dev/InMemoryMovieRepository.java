package diana.dev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class InMemoryMovieRepository implements MovieRepository{

    private final SessionFactory factory;

    public InMemoryMovieRepository(SessionFactory factory) {

        this.factory = factory;
    }

    @Override
    public void addMovie(Movie movie) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(movie);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            List<Movie> movies = session.createQuery("SELECT m FROM Movie m", Movie.class).list();
            session.getTransaction().commit();
            return movies;
        }
    }

    @Override
    public Movie findByGenre(String genre) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Movie movie = session.createQuery("SELECT m FROM Movie m WHERE m.genre = :genre", Movie.class)
                    .setParameter("genre", genre)
                    .getSingleResult();
            session.getTransaction().commit();
            return movie;
        }
    }

    @Override
    public void updateTitle(Long id, String newTitle) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Movie movie = session.find(Movie.class, id);
            if (movie != null) {
                movie.setTitle(newTitle);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteMovie(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Movie movie = session.find(Movie.class, id);
            if (movie != null) {
                session.remove(movie);
            }
            session.getTransaction().commit();
        }
    }
}
