package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Client;
import project.com.Entity.Crew;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {


    @Override
    Optional<Crew> findById(Long id);

    @Override
    List<Crew> findAll();

    @Override
    void deleteById(Long id);
}