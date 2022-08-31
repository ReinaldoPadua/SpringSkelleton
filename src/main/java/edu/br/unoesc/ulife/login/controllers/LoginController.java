package edu.br.unoesc.ulife.login.controllers;

import edu.br.unoesc.ulife.login.entities.User;
import edu.br.unoesc.ulife.login.repositories.UserRepository;
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
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/users")
    public String hello(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/login";
    }
}
