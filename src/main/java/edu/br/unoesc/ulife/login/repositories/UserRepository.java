package edu.br.unoesc.ulife.login.repositories;

import java.util.List;
import java.util.Optional;

import edu.br.unoesc.ulife.login.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
    public Optional<User> findByUsername(String username);

    public List<User> findAll();
}
