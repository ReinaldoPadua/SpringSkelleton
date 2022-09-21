package edu.br.unoesc.app.produtos.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "produto_imagens")
public class Imagem extends  EntidadeAbstrata{

    private String nome;

    private String urlArquivo;

    @ManyToOne
    @JoinColumn(name="produto_id", nullable=false)
    private Produto produto;

    public String getNome() {
        return nome;
    }

}
