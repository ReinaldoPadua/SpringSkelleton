package edu.br.unoesc.app.produtos.dtos;

import edu.br.unoesc.app.produtos.entities.Categoria;
import edu.br.unoesc.app.produtos.entities.Produto;

public class CategoriaDTO {

    private Long id;

    private String nome;

    public CategoriaDTO(Categoria categoria) {
        super();
        if (categoria.getId()!=null)
            this.id = categoria.getId();
        this.nome = categoria.getNome();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
