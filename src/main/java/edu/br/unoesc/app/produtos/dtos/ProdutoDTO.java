package edu.br.unoesc.app.produtos.dtos;

import edu.br.unoesc.app.produtos.entities.Produto;


public class ProdutoDTO {

    private Long id;

    private String nome;

    private String descricao;

    private Double valor;

    private Long categoriaId;

    private String categoria;

    public ProdutoDTO() {
        super();
    }

    public ProdutoDTO(Produto produto) {
        super();
        if (produto.getId()!=null)
            this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.categoriaId = produto.getCategoria().getId();
        this.categoria = produto.getCategoria().getNome();

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
