package edu.br.unoesc.app.produtos.repositories;

import edu.br.unoesc.app.produtos.entities.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {


    public List<Categoria> findAll();
}
