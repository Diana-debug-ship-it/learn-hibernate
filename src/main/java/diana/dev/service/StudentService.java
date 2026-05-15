package diana.dev.service;

import diana.dev.TransactionHelper;
import diana.dev.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public StudentService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Student saveStudent(Student student){
        return transactionHelper.executeInTransaction(session -> {
            session.persist(student);
            return student;
        });
    }

    public void deleteStudent(Long id) {
        transactionHelper.executeInTransaction(session -> {
            Student studentForDelete = session.find(Student.class, id);
            session.remove(studentForDelete);
        });
    }

    public Student getById(Long id) {

        try(Session session = sessionFactory.openSession()) {
            return session.find(Student.class, id);

        }
    }

    public List<Student> findAll() {
        try(Session session = sessionFactory.openSession()) {
            List<Student> studentList = session.createQuery("SELECT s FROM Student s", Student.class).list();
            studentList.forEach(System.out::println);
            return studentList;
        }
    }

    public Student updateStudent(Student student) {
        return transactionHelper.executeInTransaction(session -> {
            return session.merge(student);
        });
    }

}
