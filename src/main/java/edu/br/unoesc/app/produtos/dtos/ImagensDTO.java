package edu.br.unoesc.app.produtos.dtos;

import edu.br.unoesc.app.produtos.entities.Imagen;
public class ImagensDTO {

    private Long id;

    private String nome;

    private String urlArquivo;

    private Long produtoId;

    public ImagensDTO() {
        super();
    }

    public ImagensDTO(Imagen imagen) {
        super();
        if (imagen.getId()!=null)
            this.id = imagen.getId();
        this.nome = imagen.getNome();
        this.urlArquivo = imagen.getUrlArquivo();
        this.produtoId = imagen.getProduto().getId();
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

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}

