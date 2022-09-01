package edu.br.unoesc.ulife.login.repositories;

import java.util.List;
import java.util.Optional;

import edu.br.unoesc.ulife.login.entities.Produto;
import edu.br.unoesc.ulife.login.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>  {
    public List<Produto> findAll();
}
