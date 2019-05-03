package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Person;
import project.com.Entity.Transport;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    @Override
    Optional<Transport> findById(Long id);

    @Override
    List<Transport> findAll();

    @Override
    void deleteById(Long id);


}