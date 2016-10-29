package testask.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testask.crud.dao.UserDaoImpl;
import testask.crud.model.User;
import testask.crud.service.UserService;

import java.util.List;

/**
 * Created by Dmitriy on 27.10.2016.
 */

@Controller
public class UserController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="*", method = RequestMethod.GET)
    public String showNotFound(){
        return "404";
    }

    @RequestMapping(value="users/{pageNumber}", method = RequestMethod.GET)
    public String listUsers(@PathVariable("pageNumber") int pageNumber, Model model){

        List<User> users = this.userService.listUsers(pageNumber);

        if (users.size() == 0 || pageNumber == 0) return "404";

        model.addAttribute("user", new User());
        model.addAttribute("listUsers", users);
        model.addAttribute("pageCount", UserDaoImpl.getPageCount());

        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){

        if (user.getId() == 0 )
            this.userService.addUser(user);
        else
            this.userService.updateUser(user);

        return "redirect:/users";
    }

    @RequestMapping("/resmove{id}")
    public String removeUser(@PathVariable("id") int id){

        this.userService.removeUser(id);

        return "redirect:/users";
    }

    @RequestMapping("/edit{id}")
    public String editUser(@PathVariable("id") int id, Model model){

        model.addAttribute("user",this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers(0));

        return "users";
    }
}
