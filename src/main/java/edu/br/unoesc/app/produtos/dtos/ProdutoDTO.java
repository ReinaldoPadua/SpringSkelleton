package edu.br.unoesc.app.produtos.dtos;

import edu.br.unoesc.app.produtos.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;

    private String nome;

    private String descricao;

    private Double valor;

    private Long categoriaId;

    private String categoria;

}
