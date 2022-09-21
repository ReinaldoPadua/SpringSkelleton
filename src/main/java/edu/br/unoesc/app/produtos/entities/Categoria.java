package edu.br.unoesc.app.produtos.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "produto_categoria")
public class Categoria extends  EntidadeAbstrata{

    private String nome;

}
