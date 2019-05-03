package project.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.Entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

}