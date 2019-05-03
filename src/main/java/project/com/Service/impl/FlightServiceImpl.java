package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Crew;
import project.com.Entity.Flight;
import project.com.Repository.CrewRepository;
import project.com.Repository.FlightRepository;
import project.com.Service.CrewService;
import project.com.Service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void createFlight(Flight flight) {
        flightRepository.save(flight);
    }
}
