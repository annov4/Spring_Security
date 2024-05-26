package annov4.crud.crud.controller;

import annov4.crud.crud.model.Role;
import annov4.crud.crud.model.User;
import annov4.crud.crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("userForm") User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1L, "USER"));
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/login";
    }
}