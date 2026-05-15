package diana.dev.service;

import diana.dev.TransactionHelper;
import diana.dev.model.Post;
import diana.dev.model.User;
import diana.dev.model.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public UserService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public User saveUser(String name, String email, String bio){
        return transactionHelper.executeInTransaction(session -> {
            User user = new User(name, email);
            UserProfile profile = new UserProfile(bio, LocalDateTime.now());
            user.setUserProfile(profile);
            profile.setUser(user);
            session.persist(user);
            return user;
        });
    }

    public void deleteUser(Long id) {
        transactionHelper.executeInTransaction(session -> {
                User userForDelete = session.find(User.class, id);
                session.remove(userForDelete);
        });
    }

    public User getById(Long id) {
        return transactionHelper.executeInTransaction(session -> {
            User user = session.find(User.class, id);
            return user;
        });
    }

    public List<User> findAll() {
        return transactionHelper.executeInTransaction(session -> {
            List<User> userList = session.createQuery("SELECT u FROM User u", User.class).list();
            userList.forEach(System.out::println);
            return userList;
        });
    }

    public User updateUser(User user) {
        return transactionHelper.executeInTransaction(session -> {
            return session.merge(user);
        });
    }

    public Post savePost(String text, User user) {
        return transactionHelper.executeInTransaction(session -> {
            Post post = new Post(text, user);
            session.persist(post);
            return post;
        });
    }

    public List<Post> getUserPosts(Long userId) {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("SELECT p FROM Post p where p.user.id = :user_id", Post.class).setParameter("user_id", userId).list();
        });
    }

    public List<Post> findAllPosts() {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("SELECT p FROM Post p left join fetch p.user", Post.class).list();
        });
    }
}
