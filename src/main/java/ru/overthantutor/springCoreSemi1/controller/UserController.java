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

    @GetMapping("/users")
    public String readAll(Model model) {
        List<User> list = userService.readAll();
        model.addAttribute("users", list);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.deleteById(id);
        return "redirect:/users";
    }
}
