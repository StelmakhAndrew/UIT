package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Client;
import project.com.Entity.DTO.FlightDTO;
import project.com.Entity.Flight;
import project.com.Service.FlightService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public ResponseEntity<List<FlightDTO>> getAllFlight() {
        List<FlightDTO> flightsDTO = flightService.findAllFlight().stream().map(FlightDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(flightsDTO);
    }

    @RequestMapping(value = "/flights/{id}", method = RequestMethod.GET)
    public ResponseEntity<FlightDTO> getFlight(@PathVariable("id") Long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight ==null) return ResponseEntity.notFound().build();
        FlightDTO flightDTO = new FlightDTO(flight);
        return ResponseEntity.ok(flightDTO);
    }

    @RequestMapping(value = "/flights/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<FlightDTO> deleteFlight(@PathVariable("id") Long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight ==null) return ResponseEntity.notFound().build();
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/flights/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FlightDTO> updateFlight(@RequestBody Flight flight, @PathVariable Long id) {
        Flight oldFlight = flightService.findFlightById(id);
        if (oldFlight == null) {
            return ResponseEntity.notFound().build();
        } else {
            flightService.updateFlight(flight);
            FlightDTO flightDTO = new FlightDTO(flight);
            return ResponseEntity.ok(flightDTO);
        }
    }

    @RequestMapping(value = "/addFlight", method = RequestMethod.POST)
    public ResponseEntity<FlightDTO> addClient(@RequestBody Flight flight) {
        flightService.createFlight(flight);
        FlightDTO flightDTO = new FlightDTO(flight);
        return ResponseEntity.ok(flightDTO);
    }
}
