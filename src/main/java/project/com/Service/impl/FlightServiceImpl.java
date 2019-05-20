package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Crew;
import project.com.Entity.DTO.FlightDTO;
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

    @Autowired
    private CrewService crewService;


    @Override
    public Flight createFlight(FlightDTO flight) {
        Flight newFlight = new Flight();
        newFlight.setName(flight.getName());

        if (flight.getCrewId() != null) {
            Crew currentCrew = crewService.findCrewById(flight.getCrewId());

            if (currentCrew == null) return null;
            newFlight.setCrew(currentCrew);
            currentCrew.setFlight(newFlight);
        }

        flightRepository.save(newFlight);
        return newFlight;
    }

    @Override
    public Flight updateFlight(FlightDTO flight) {
        return createFlight(flight);
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
