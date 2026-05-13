package diana.dev;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext("diana.dev");

        SessionFactory sessionFactory = configApplicationContext.getBean(SessionFactory.class);
//        Session session = sessionFactory.openSession();
//
//        Student student1 = new Student("Vasya", 22);
//        Student student2 = new Student("Pasha", 45);
//
//        session.beginTransaction();
//        // добавление записей в БД
//        session.persist(student1);
//        session.persist(student2);
//        session.getTransaction().commit();
//
//        Student studentById = session.find(Student.class, 1L);
//        System.out.println(studentById);
//
//        Student studentById2 = session.createQuery(
//                "SELECT s FROM Student s where s.id = :id ", Student.class)
//                .setParameter("id", 2)
//                .getSingleResult();
//        System.out.println(studentById2);
//
//        session.beginTransaction();
//
//        // обновление данных
//        Student studentForUpdate = session.find(Student.class, 1L);
//        studentForUpdate.setAge(30);
//        studentForUpdate.setName("Dima");
//
//        // удаление из БД
////        Student studentForDelete = session.find(Student.class, 2L);
////        session.remove(studentForDelete);
//
////        session.createQuery(
////                "DELETE FROM Student s WHERE s.id = 2")
////                .executeUpdate();
//
////        session.createNativeQuery("delete from students s where s.id=2").executeUpdate();
//
//        session.getTransaction().commit();
//
//        List<Student> studentList = session.createQuery("SELECT s FROM Student s", Student.class).list();
//        studentList.forEach(System.out::println);
//
//        Student studentByName = session.createQuery("SELECT s FROM Student s WHERE s.name= :name", Student.class)
//                .setParameter("name", "Pasha")
//                .getSingleResult();
//
//        System.out.println("Нашли по имени: "+studentByName);
////        System.out.println(studentForUpdate);
//


        MovieRepository repository = new InMemoryMovieRepository(sessionFactory);
        repository.addMovie(new Movie("Титаник", "сопли", 1990));
        repository.addMovie(new Movie("Убить Билла", "клоунада", 2008));
        repository.getAllMovies().forEach(System.out::println);
        System.out.println(repository.findByGenre("клоунада"));
        repository.updateTitle(1L, "Алилуйся");
        repository.deleteMovie(2L);
        repository.getAllMovies().forEach(System.out::println);

//        session.close();
    }
}