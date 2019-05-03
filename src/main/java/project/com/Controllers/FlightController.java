package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Client;
import project.com.Entity.Flight;
import project.com.Service.FlightService;

import java.util.List;


@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public ResponseEntity<List<Flight>> getAllFlight() {
        List<Flight> flights = flightService.findAllFlight();
        return ResponseEntity.ok(flights);
    }

    @RequestMapping(value = "/flights/{id}", method = RequestMethod.GET)
    public ResponseEntity<Flight> getFlight(@PathVariable("id") Long id) {
        Flight flight = flightService.findFlightById(id).orElse(null);
        return ResponseEntity.ok(flight);
    }

    @RequestMapping(value = "/flights/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Flight> deleteFlight(@PathVariable("id") Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/flights/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight, @PathVariable Long id) {
        Flight oldFlight = flightService.findFlightById(id).orElse(null);
        if (oldFlight == null) {
            return ResponseEntity.notFound().build();
        } else {
            flightService.updateFlight(flight);
            return ResponseEntity.ok(flight);
        }
    }

    @RequestMapping(value = "/addFlight", method = RequestMethod.POST)
    public ResponseEntity<Flight> addClient(@RequestBody Flight flight) {
        flightService.createFlight(flight);
        return ResponseEntity.ok(flight);
    }
}
