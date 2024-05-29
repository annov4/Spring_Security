package annov4.crud.crud.controller;

import annov4.crud.crud.model.User;
import annov4.crud.crud.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegistrationAndLoginController {

    private final UserServiceImpl userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User user, Model model) {

        if (userService.userExists(user.getName())) {
            model.addAttribute("error", "User with the same name already exists.");
            return "registration";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userForm") User user, Model model) {
        User foundUser = (User) userService.loadUserByUsername(user.getName());

        if (!(foundUser != null && foundUser.getPassword().equals(user.getPassword()))) {

            if (foundUser.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin/users";
            }
            if (foundUser.getRoles().stream().anyMatch(role -> role.getAuthority().equals("ROLE_USER"))) {
                return "redirect:/home";
            }
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
        return "redirect:/";
    }
}