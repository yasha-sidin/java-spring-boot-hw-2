package ru.overthantutor.springCoreSemi1.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.overthantutor.springCoreSemi1.model.User;
import ru.overthantutor.springCoreSemi1.service.UserService;

import java.util.List;
import java.util.logging.Level;

/**
 * Spring controller which control user service
 */
@AllArgsConstructor
@Controller
@Log
public class UserController {
    private final UserService userService;

    /**
     * Forming main panel
     * @param model   html model
     * @param request client request
     * @return        user-list.html
     */
    @GetMapping("/users")
    public String readAll(Model model, HttpServletRequest request) {
        List<User> list = userService.readAll();
        model.addAttribute("users", list);
        log.log(Level.INFO, "All users were imported as successfully! Request from: " + "ip: " + request.getRemoteHost() + ", port: " + request.getRemotePort());
        return "user-list";
    }

    /**
     * Forming user creating panel
     * @param user    empty user
     * @param request client request
     * @return        user-create.html
     */
    @GetMapping("/user-create")
    public String createUserForm(User user, HttpServletRequest request) {
        log.log(Level.INFO, "Creation user form started as successfully! Request from: " + "ip: " + request.getRemoteHost() + ", port: " + request.getRemotePort());
        return "user-create";
    }

    /**
     * Creating user
     * @param user    creating user
     * @param request client request
     * @return        redirect:/users
     */
    @PostMapping("/user-create")
    public String createUser(User user, HttpServletRequest request) {
        if (user.getFirstname().isEmpty() || user.getLastname().isEmpty()) {
            log.log(Level.INFO, "Creation form is empty! Request from: " + "ip: " + request.getRemoteHost() + ", port: " + request.getRemotePort());
            return "redirect:/users";
        }
        log.log(Level.INFO, user + " was created as successfully! Request from: " + "ip: " + request.getRemoteHost() + ", port: " + request.getRemotePort());
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Deleting user
     * @param id      user id
     * @param request client request
     * @return        redirect:/users
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id, HttpServletRequest request) {
        User user = userService.deleteById(id);
        log.log(Level.INFO, user + " was deleted as successfully! Request from: " + "ip: " + request.getRemoteHost() + ", port: " + request.getRemotePort());
        return "redirect:/users";
    }

    /**
     * Forming user updating panel
     * @param id      user id
     * @param model   html model
     * @param request client request
     * @return        user-update.html or redirect:/users
     */
    @GetMapping("user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model, HttpServletRequest request) {
        User user = userService.getUserById(id);
        if (user == null) {
            log.log(Level.INFO, user + " doesn't exists! Request from: " + "ip: " + request.getRemoteHost() + ", port: " + request.getRemotePort());
            return "redirect:/users";
        }
        model.addAttribute(userService.getUserById(id));
        log.log(Level.INFO, user + " can be updated! Request from: " + "ip: " + request.getRemoteHost() + ", port: " + request.getRemotePort());
        return "user-update";
    }

    /**
     * Updating user
     * @param user    user with updated data
     * @param request client request
     * @return        redirect:/users
     */
    @PostMapping("user-update")
    public String updateUser(User user, HttpServletRequest request) {
        User updatedUser = userService.getUserById(user.getId());
        if (userService.updateUser(user) == null) {
            log.log(Level.INFO, updatedUser + " was not updated! Request from: " + "ip: " + request.getRemoteHost() + ", port: " + request.getRemotePort());
        }
        log.log(Level.INFO, updatedUser + " was updated to " + user + " as successfully! Request from: " + "ip: " + request.getRemoteHost() + ", port: " + request.getRemotePort());
        return "redirect:/users";
    }
}
