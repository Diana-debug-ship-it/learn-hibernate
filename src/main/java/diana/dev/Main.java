package diana.dev;

import diana.dev.model.Group;
import diana.dev.model.Profile;
import diana.dev.model.Student;
import diana.dev.model.User;
import diana.dev.service.GroupService;
import diana.dev.service.ProfileService;
import diana.dev.service.StudentService;
import diana.dev.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext("diana.dev");

        SessionFactory sessionFactory = configApplicationContext.getBean(SessionFactory.class);
        StudentService studentService = configApplicationContext.getBean(StudentService.class);
        ProfileService profileService = configApplicationContext.getBean(ProfileService.class);
        GroupService groupService = configApplicationContext.getBean(GroupService.class);

//        MovieRepository repository = new HibernateMovieRepository(sessionFactory);
//        repository.addMovie(new Movie("Титаник", "сопли", 1990));
//        repository.addMovie(new Movie("Убить Билла", "клоунада", 2008));
//        repository.getAllMovies().forEach(System.out::println);
//        System.out.println(repository.findByGenre("клоунада"));
//        repository.updateTitle(1L, "Алилуйся");
//        repository.deleteMovie(2L);
//        repository.getAllMovies().forEach(System.out::println);

//        session.close();

//        Profile profile1 = new Profile("My bio", LocalDateTime.now(), student1);
//        profileService.saveProfile(profile1);
//
//        Group group1 = groupService.saveGroup("1", 2027L);
//        Group group2 = groupService.saveGroup("2", 2025L);
//        Group group3 = groupService.saveGroup("3", 2030L);
//
//
//        Student student1 = new Student("Erik", 34, group1);
//        Student student2 = new Student("Alice", 21, group1);
//
//        studentService.saveStudent(student1);
//        studentService.saveStudent(student2);
//
//        groupService.getStudentList(1L).forEach(System.out::println);
//
//        System.out.println("----------------------------------------");
//
//        groupService.findAll().forEach(System.out::println);

        UserService userService = configApplicationContext.getBean(UserService.class);
        User user1 = userService.saveUser("Anna", "anna@mail.com", "my bio");
        User user2 = userService.saveUser("Kate", "kate@mail.com", "actress model");
        User user3 = userService.saveUser("Dima", "dima@mail.com", "loh");

        userService.savePost("spam1", user1);
        userService.savePost("spam2", user1);
        userService.savePost("spam3", user2);
        userService.savePost("spam4", user2);
        userService.savePost("spam5", user3);

        //userService.getUserPosts(user1.getId()).forEach(System.out::println);
        System.out.println("__________________________");
        userService.findAllPosts().forEach(System.out::println);
    }
}