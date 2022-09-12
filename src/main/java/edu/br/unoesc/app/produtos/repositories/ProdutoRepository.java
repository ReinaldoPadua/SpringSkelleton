package edu.br.unoesc.app.produtos.repositories;

import edu.br.unoesc.app.produtos.entities.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProdutoRepository extends CrudRepository<Produto, String>  {

    public Produto findById(Long id);

    public Produto findByNome(String nome);

    public List<Produto> findAll();
}
