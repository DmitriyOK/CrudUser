package testask.crud.service;

import testask.crud.model.User;

import java.util.List;

/**
 * Created by Dmitriy on 26.10.2016.
 */
public interface UserService {

    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public List<User> listUsers(int pageNumber);

    public List<User> getUserAsListById(int id);
}
