package edu.br.unoesc.app.produtos.repositories;

import edu.br.unoesc.app.produtos.entities.Imagem;
import edu.br.unoesc.app.produtos.entities.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ImagemRepository extends CrudRepository<Imagem, String> {
    @Query("from Imagem imagem where  imagem.produto.id = :produtoId and imagem.id = :id")
    public Imagem findByProdutoAndId(@Param("produtoId") Long produtoId,@Param("id") Long id);
}
