package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Crew;
import project.com.Entity.Flight;

@Service
public interface FlightService {

    void createFlight(Flight flight);
}