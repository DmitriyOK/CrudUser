package testask.crud.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import testask.crud.model.User;

import java.util.List;

/**
 * Created by Dmitriy on 26.10.2016.
 */


@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger("UserDaoImpl");

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.debug(String.format("User %s was added", user.getName()));

    }

    public void updateUSer(User user) {

        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);

        logger.debug(String.format("User %s was updated", user.getName()));

    }

    public void removeUser(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if (user != null)
            session.delete(user);

        logger.debug(String.format("User %s was deleted", user.getName()));
    }

    public User getUserById(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        logger.debug(String.format("User %s was loaded", user.getName()));

        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers() {

        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();

        for(User user : users)
            logger.debug("User: "+ user.toString());

        return users;
    }
}
