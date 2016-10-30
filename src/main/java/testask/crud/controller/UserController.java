package testask.crud.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testask.crud.dao.UserDaoImpl;
import testask.crud.model.User;
import testask.crud.model.Validator;
import testask.crud.service.UserService;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Dmitriy on 27.10.2016.
 */

@Controller
public class UserController {

    Logger logger = LogManager.getLogger("UerController");

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="*", method = RequestMethod.GET)
    public String showNotFound(Model model){

        model.addAttribute("statusCode", new Integer(404));
        model.addAttribute("description", "Page not found");
        return "error";
    }

    @RequestMapping(value="users/{pageNumber}", method = RequestMethod.GET)
    public String listUsers(@PathVariable("pageNumber") int pageNumber, Model model){

        List<User> users = this.userService.listUsers(pageNumber);

        if (users.size() == 0 || pageNumber == 0) return "error";

        model.addAttribute("user", new User());
        model.addAttribute("listUsers", users);
        model.addAttribute("pageCount", UserDaoImpl.getTotalPages());
        model.addAttribute("resultsFound", UserDaoImpl.getTotalResults());

        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model){

        if (!Validator.validateDataUser(user)) {
            model.addAttribute("statusCode", new Integer(400));
            model.addAttribute("description", "User data not valid. \n Age must be 2 symbols. \n Name must be no more 45 symbols, without cyrillic, digits and space in the end.");
            logger.debug("Wrong data user. " +user);
            return "error";
        }

        if (user.getId() == 0 )
            this.userService.addUser(user);
        else
            this.userService.updateUser(user);

        return "redirect:/users/1";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){

        this.userService.removeUser(id);

        return "redirect:/users/1";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){

        model.addAttribute("user",this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.getUserAsListById(id));

        return "edit";
    }
    @RequestMapping("/users/search")
    public String searchUserById(@RequestParam(value = "id") int id, Model model){

        try {
            List<User> users = this.userService.getUserAsListById(id);

            model.addAttribute("user",this.userService.getUserById(id));
            model.addAttribute("listUsers", users);
            model.addAttribute("resultsFound", users.size());

            return "edit";

        }catch (ObjectNotFoundException e){

            model.addAttribute("statusCode", new Integer(404));
            model.addAttribute("description", "User with current id not found.");

            logger.debug(String.format("Users with id: %s not found.",id));

            return "error";
        }
    }

    @RequestMapping("/users/pagingby")
    public String setResultsByPage(@RequestParam(value = "results") int resultsOnPage, Model model){

            if (!Validator.validateNumber(resultsOnPage)) {

                model.addAttribute("statusCode", new Integer(404));
                model.addAttribute("description", "Incorrect value: "+resultsOnPage);

                return "error";
            }

            UserDaoImpl.setPageSize(resultsOnPage);

            return "redirect:/users/1";
    }
}
