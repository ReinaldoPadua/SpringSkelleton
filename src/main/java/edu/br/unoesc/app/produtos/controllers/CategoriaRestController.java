package edu.br.unoesc.app.produtos.controllers;

import edu.br.unoesc.app.produtos.dtos.CategoriaDTO;
import edu.br.unoesc.app.produtos.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoriaRestController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity buscarTodasCategorias() {
        List<CategoriaDTO> listaDeCategorias = categoriaService.listarTodas();
        return ResponseEntity.ok(listaDeCategorias);
    }

    @GetMapping("/categorias/{categoriaId}")
    public ResponseEntity buscarCategoriaPorId(@PathVariable Long categoriaId) {
        try{
            CategoriaDTO categoriaDTO = categoriaService.buscarCategoriaPorId(categoriaId);
            return ResponseEntity.ok(categoriaDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/categorias")
    public ResponseEntity salvarNovaCategoria(@RequestBody CategoriaDTO novaCategoriaDTO) {
        try{
            novaCategoriaDTO = categoriaService.salvarNovaCategoria(novaCategoriaDTO);
            return ResponseEntity.ok(novaCategoriaDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/categorias")
    public ResponseEntity atualizarCategoria(@RequestBody CategoriaDTO categoriaAtualizadaDTO) {
        try{
            categoriaAtualizadaDTO = categoriaService.atualizarCategoria(categoriaAtualizadaDTO);
            return ResponseEntity.ok(categoriaAtualizadaDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/categorias/{categoriaId}")
    public ResponseEntity deletarCategoria(@PathVariable Long categoriaId) {
        try{
            categoriaService.deletarCategoria(categoriaId);
            return ResponseEntity.ok("Deletado com suesso");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
