package edu.br.unoesc.app.produtos.dtos;

import edu.br.unoesc.app.produtos.entities.Imagem;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImagemDTO {

    private Long id;

    private String nome;

    private String urlArquivo;

}
