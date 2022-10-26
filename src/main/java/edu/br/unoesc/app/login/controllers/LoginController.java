package edu.br.unoesc.app.login.controllers;

import edu.br.unoesc.app.login.entities.User;
import edu.br.unoesc.app.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model) {

        return "login/login_view";
    }

    @GetMapping("/")
    public String home(Model model) {

        return "login/home_view";
    }

    @GetMapping("/users")
    public String hello(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("usuariosAtivos", users);
        return "login/user_view";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/login";
    }
}
