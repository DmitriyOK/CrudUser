package testask.crud.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
import testask.crud.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 26.10.2016.
 */


@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger("UserDaoImpl");

    private SessionFactory sessionFactory;
    private static int totalResults;
    private static int totalPages;
    private static int pageSize=5;
    private static ArrayList<Integer> aPages;

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

        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery("from User");
        int firstResult=(pageNumber*pageSize)-pageSize;

        totalResults = query.list().size();

        aPages = createArrayPages(pageNumber, totalResults);

        logger.debug(String.format("Max results: %s Max pages: %s", totalResults, totalPages));

        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

        List<User> users = query.list();

        for(User user : users)
            logger.debug("User: "+ user.toString());

        return users;
    }

    private ArrayList<Integer> createArrayPages(int pageNumber, int totalResults){

        int pageCount = totalResults / pageSize;

        totalPages =  totalResults % pageSize==0 ? pageCount : pageCount +1 ;

        ArrayList<Integer> pages = new ArrayList<Integer>();

        int paginationSize = 7;

        if (totalPages > paginationSize) {

            if (pageNumber < paginationSize) {
                for (int i = 0; i < paginationSize; i++) {
                    pages.add(1 + i);
                }
                return pages;
            }

            if (totalPages - pageNumber <= 3)  {
                for (int i = 6; i >= 0; i--) {
                   pages.add(totalPages - i);
                }
            }
            else {
                for (int i = -3; i < paginationSize - 3; i++) {
                    pages.add(pageNumber + i);
                }
            }
        }
        if (totalPages <= paginationSize){

            for (int i = 0; i < totalPages ; i++) {
                pages.add(i+1);
            }
        }
                logger.debug(pages);

            return pages;
    }

    public  ArrayList<Integer> getTotalPages() {
        return aPages;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
