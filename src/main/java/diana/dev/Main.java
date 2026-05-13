package diana.dev;

import diana.dev.service.StudentService;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext("diana.dev");

        SessionFactory sessionFactory = configApplicationContext.getBean(SessionFactory.class);

        Student student1 = new Student("Erik", 34);
        Student student2 = new Student("Alice", 21);

        StudentService studentService = configApplicationContext.getBean(StudentService.class);

//        MovieRepository repository = new InMemoryMovieRepository(sessionFactory);
//        repository.addMovie(new Movie("Титаник", "сопли", 1990));
//        repository.addMovie(new Movie("Убить Билла", "клоунада", 2008));
//        repository.getAllMovies().forEach(System.out::println);
//        System.out.println(repository.findByGenre("клоунада"));
//        repository.updateTitle(1L, "Алилуйся");
//        repository.deleteMovie(2L);
//        repository.getAllMovies().forEach(System.out::println);

        studentService.saveStudent(student1);
        studentService.saveStudent(student2);

//        session.close();
    }
}