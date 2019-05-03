package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Crew;
import project.com.Entity.Flight;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Override
    Optional<Flight> findById(Long id);

    @Override
    List<Flight> findAll();

    @Override
    void deleteById(Long id);
}