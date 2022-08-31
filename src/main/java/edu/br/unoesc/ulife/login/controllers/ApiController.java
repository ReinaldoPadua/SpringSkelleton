package edu.br.unoesc.ulife.login.controllers;

import edu.br.unoesc.ulife.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.br.unoesc.ulife.login.entities.User;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
