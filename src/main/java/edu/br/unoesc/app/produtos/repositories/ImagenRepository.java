package edu.br.unoesc.app.produtos.repositories;

import org.springframework.stereotype.Repository;
import edu.br.unoesc.app.produtos.entities.Imagen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ImagenRepository extends CrudRepository<Imagen, String>  {

    public Imagen findById(Long nome);

    public List<Imagen> findAll();
}
