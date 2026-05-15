package diana.dev.service;

import diana.dev.TransactionHelper;
import diana.dev.model.Profile;
import diana.dev.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfileService {
    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public ProfileService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Profile saveProfile(Profile profile) {

        return transactionHelper.executeInTransaction(session -> {
            session.persist(profile);
            return profile;
        });
    }
}
