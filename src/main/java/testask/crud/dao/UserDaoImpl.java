package testask.crud.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
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

    private static int totalPages;
    private static int totalResults;
    private static int pageSize=5;

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
        logger.debug("User data "+ user);
    }

    public void removeUser(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if (user != null)
            session.delete(user);

        logger.debug(String.format("User %s was deleted", user.getName()));
    }

    public User getUserById(int id) throws ObjectNotFoundException{

        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        logger.debug(String.format("User %s was loaded", user.getName()));

        return user;
    }

    @SuppressWarnings("uncheked")
    public List<User> getUserAsListById(int id) {

        Session session = this.sessionFactory.getCurrentSession();

        List<User> userAsList = session.createQuery("from User U where U.id="+id).list();

        logger.debug(String.format("User was loaded. %s", userAsList));

        return userAsList;
    }


    @SuppressWarnings("unchecked")
    public List<User> listUsers(int pageNumber) {

        int firstResult=(pageNumber*pageSize)-pageSize;

        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");

        totalResults = query.list().size();
        totalPages =  totalResults % pageSize==0 ? totalResults / pageSize : totalResults / pageSize +1 ;

        logger.debug(String.format("Max results: %s Max pages: %s", totalResults, totalPages));

        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

        List<User> users = query.list();

        for(User user : users)
            logger.debug("User: "+ user.toString());

        return users;
    }

    public static int getTotalPages() {
        return totalPages;
    }

    public static int getTotalResults() {
        return totalResults;
    }

    public static void setPageSize(int pageSize) {
        UserDaoImpl.pageSize = pageSize;
    }
}
