package edu.br.unoesc.ulife.login.controllers;

import edu.br.unoesc.ulife.login.entities.Produto;
import edu.br.unoesc.ulife.login.services.ProdutoService;
import edu.br.unoesc.ulife.login.services.UserService;
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
    UserService userService;
    @Autowired
    ProdutoService produtoService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {

        return userService.getActiveUsers();
    }

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> getProducts() {

            return produtoService.getActiveProduto();
    }
}
