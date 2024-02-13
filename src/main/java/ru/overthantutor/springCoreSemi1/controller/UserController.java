package ru.overthantutor.springCoreSemi1.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.overthantutor.springCoreSemi1.model.User;
import ru.overthantutor.springCoreSemi1.service.UserService;

import java.util.List;

/**
 * Spring controller which control user service
 */
@AllArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    /**
     * Forming main panel
     * @param model html model
     * @return      user-list.html
     */
    @GetMapping("/users")
    public String readAll(Model model) {
        List<User> list = userService.readAll();
        model.addAttribute("users", list);
        return "user-list";
    }

    /**
     * Forming user creating panel
     * @param user empty user
     * @return     user-create.html
     */
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    /**
     * Creating user
     * @param user creating user
     * @return     redirect:/users
     */
    @PostMapping("/user-create")
    public String createUser(User user) {
        if (user.getFirstname().isEmpty() || user.getLastname().isEmpty()) return "redirect:/users";
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Deleting user
     * @param id user id
     * @return   redirect:/users
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Forming user updating panel
     * @param id    user id
     * @param model html model
     * @return      user-update.html
     */
    @GetMapping("user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "user-update";
    }

    /**
     * Updating user
     * @param user user with updated data
     * @return     redirect:/users
     */
    @PostMapping("user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}
