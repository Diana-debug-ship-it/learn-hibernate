package diana.dev.service;

import diana.dev.TransactionHelper;
import diana.dev.model.Group;
import diana.dev.model.Student;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public GroupService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Group saveGroup(String number, Long graduationYear) {
        return transactionHelper.executeInTransaction(session -> {
            var group = new Group(number, graduationYear);
            session.persist(group);
            return group;
        });
    }


    public List<Student> getStudentList(Long id) {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("SELECT s FROM Student s WHERE s.group.id = :group_id", Student.class).setParameter("group_id", id).list();
        });
    }


    public List<Group> findAll() {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("""
                SELECT g FROM Group g
                left join fetch g.studentList
                """, Group.class).list();
        });
    }
}
