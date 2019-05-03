package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Client;
import project.com.Entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    @Override
    Optional<Client> findById(Long id);

    @Override
    List<Client> findAll();

    @Override
    void deleteById(Long id);

}