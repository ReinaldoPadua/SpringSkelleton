package edu.br.unoesc.ulife.login.services;

import edu.br.unoesc.ulife.login.entities.Produto;
import edu.br.unoesc.ulife.login.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    public ResponseEntity<List<Produto>> getActiveProduto(){

        List<Produto> produto = produtoRepository.findAll();
        List<Produto> actProduto = new ArrayList<>();
        for (Produto produto1:produto) {
            if(produto1.getId() != null)
                actProduto.add(produto1);
        }
        return ResponseEntity.ok(actProduto);

    }
}
