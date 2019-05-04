package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Crew;
import project.com.Entity.Flight;
import project.com.Repository.CrewRepository;
import project.com.Repository.FlightRepository;
import project.com.Service.CrewService;
import project.com.Service.FlightService;

import java.util.List;
import java.util.Optional;

/**
 * реалізація сервісу
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void createFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void updateFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public List<Flight> findAllFlight() {
        return flightRepository.findAll();
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);

    }
}
