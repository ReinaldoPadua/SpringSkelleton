package edu.br.unoesc.app.produtos.controllers;

import edu.br.unoesc.app.produtos.dtos.ImagemDTO;
import edu.br.unoesc.app.produtos.dtos.ProdutoDTO;
import edu.br.unoesc.app.produtos.services.ProdutoImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos/{produtoId}/imagens")
public class ProdutoImagemRestController {

    @Autowired
    ProdutoImagemService produtoImagemService;

    @GetMapping("/")
    public ResponseEntity listaImagensDoProduto(@PathVariable Long produtoId) {
        try {
            List<ImagemDTO> imagens = produtoImagemService.listarTodasImagensDoProduto(produtoId);
            return ResponseEntity.ok(imagens);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{imagemId}")
    public ResponseEntity buscaImagemPorProdutoIdEimagemId(@PathVariable Long produtoId,@PathVariable Long imagemId) {
        try {
            ImagemDTO imagemDTO = produtoImagemService.buscaImagemPorProdutoIdEimagemId(produtoId,imagemId);
            return ResponseEntity.ok(imagemDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity salvarNovoProduto(@PathVariable Long produtoId,@RequestBody ImagemDTO novaImagemDTO) {
        try {
            novaImagemDTO = produtoImagemService.salvarNovaImagem(produtoId,novaImagemDTO);
            return ResponseEntity.ok(novaImagemDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/")
    public ResponseEntity atualizarProduto(@PathVariable Long produtoId,@RequestBody ImagemDTO imagemAtualizadaDTO) {
        try {
            imagemAtualizadaDTO = produtoImagemService.atualizarImagem(produtoId,imagemAtualizadaDTO);
            return ResponseEntity.ok(imagemAtualizadaDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{imagemId}")
    public ResponseEntity deletarProdutoPorId(@PathVariable Long produtoId,@PathVariable Long imagemId) {
        try {
            produtoImagemService.deletarImagem(produtoId, imagemId);
            return ResponseEntity.ok("Deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
