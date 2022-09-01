package edu.br.unoesc.ulife.login.controllers;


import edu.br.unoesc.ulife.login.entities.Produto;
import edu.br.unoesc.ulife.login.repositories.ProdutoRepository;
import edu.br.unoesc.ulife.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProdutoControler {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/produto")
    public String produto(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "produto";
    }
}
