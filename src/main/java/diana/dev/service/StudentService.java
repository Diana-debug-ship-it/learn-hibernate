package diana.dev.service;

import diana.dev.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final SessionFactory sessionFactory;

    public StudentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Student saveStudent(Student student){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        // добавление записей в БД
        session.persist(student);
        session.getTransaction().commit();
        session.close();
        return student;
    }

    public void deleteStudent(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        удаление из БД
        Student studentForDelete = session.find(Student.class, id);
        session.remove(studentForDelete);
//
//        session.createQuery(
//                "DELETE FROM Student s WHERE s.id = 2")
//                .executeUpdate();
//
//        session.createNativeQuery("delete from students s where s.id=2").executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public Student getById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student student = session.find(Student.class, id);
//        Student studentById2 = session.createQuery(
//                "SELECT s FROM Student s where s.id = :id ", Student.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        System.out.println(studentById2);
        session.getTransaction().commit();
        session.close();
        return student;
    }

    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Student> studentList = session.createQuery("SELECT s FROM Student s", Student.class).list();
        studentList.forEach(System.out::println);
        session.close();
        return studentList;
    }

    public Student updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        student = session.merge(student);
        session.getTransaction().commit();
        session.close();
        return student;
    }


    //        session = sessionFactory.openSession();
//        student1 = session.merge(student1);
//
//        session.beginTransaction();
//        student1.setName("Tolik");
//
//        session.detach(student1);
//        student1.setAge(1000);
//
//        session.getTransaction().commit();
//        session.close();


//        // обновление данных
//        Student studentForUpdate = session.find(Student.class, 1L);
//        studentForUpdate.setAge(30);
//        studentForUpdate.setName("Dima");

    // поиск по имени
//        Student studentByName = session.createQuery("SELECT s FROM Student s WHERE s.name= :name", Student.class)
//                .setParameter("name", "Pasha")
//                .getSingleResult();
}
