package edu.br.unoesc.app.produtos.controllers;

import edu.br.unoesc.app.produtos.dtos.ProdutoDTO;
import edu.br.unoesc.app.produtos.entities.Produto;
import edu.br.unoesc.app.produtos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdutoRestController {

    @Autowired
    ProdutoService produtoService;


    @GetMapping("/produtos")
    public ResponseEntity buscarTodosProdutos() {
        List<ProdutoDTO> listaDeProdutos = produtoService.listarTodos();
        return ResponseEntity.ok(listaDeProdutos);
    }

    @GetMapping("/produtos/{produtoId}")
    public ResponseEntity buscarProdutoPorId(@PathVariable Long produtoId) {
        try {
            ProdutoDTO produtoDTO = produtoService.buscaProdutoPorId(produtoId);
            return ResponseEntity.ok(produtoDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/produtos")
    public ResponseEntity salvarNovoProduto(@RequestBody ProdutoDTO novoProdutoDTO) {
        try {
            novoProdutoDTO = produtoService.salvarNovoProduto(novoProdutoDTO);
            return ResponseEntity.ok(novoProdutoDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/produtos")
    public ResponseEntity atualizarProduto(@RequestBody ProdutoDTO produtoAtualizadoDTO) {
        try {
            produtoAtualizadoDTO = produtoService.atualizarProduto(produtoAtualizadoDTO);
            return ResponseEntity.ok(produtoAtualizadoDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/produtos/{produtoId}")
    public ResponseEntity deletarProdutoPorId(@PathVariable Long produtoId) {
        try {
            produtoService.deletarProduto(produtoId);
            return ResponseEntity.ok("Deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
