package edu.br.unoesc.app.produtos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdutoController {

    @GetMapping("/produtos")
    public String produtos() {

        return "produto/produto_view";
    }

    @GetMapping("/produtos/{produtoId}")
    public String produtosDetalhes() {

        return "produto/produto_detalhe_view";
    }

}
