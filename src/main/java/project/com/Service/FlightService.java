package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Crew;
import project.com.Entity.Flight;

import java.util.List;
import java.util.Optional;

/**
 * сервіс для маршруту
 */
@Service
public interface FlightService {

    void createFlight(Flight flight);

    void updateFlight(Flight flight);

    Flight findFlightById(Long id);

    List<Flight> findAllFlight();

    void deleteFlight(Long id);
}