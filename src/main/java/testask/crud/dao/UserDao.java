package testask.crud.dao;

import testask.crud.model.User;

import java.util.List;

/**
 * Created by Dmitriy on 26.10.2016.
 */
public interface UserDao {

    public void addUser(User user);

    public void updateUSer(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public List<User> listUsers(int pageNumber);
}