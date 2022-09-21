package edu.br.unoesc.app.produtos.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto extends  EntidadeAbstrata {

    private String nome;

    private String descricao;

    private Double valor;

    @ManyToOne
    @JoinColumn(nullable = false, name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Imagem> imagens;

}
