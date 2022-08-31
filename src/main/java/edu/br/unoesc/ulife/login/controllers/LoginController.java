package edu.br.unoesc.ulife.login.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        return "hello";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/login";
    }
}
