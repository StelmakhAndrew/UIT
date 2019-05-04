package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.*;
import project.com.Entity.DTO.CrewDTO;
import project.com.Service.CrewService;
import project.com.Service.FlightService;
import project.com.Service.PersonService;
import project.com.Service.TransportService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class CrewController {

    @Autowired
    private CrewService crewService;

    @Autowired
    private PersonService personService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/crews", method = RequestMethod.GET)
    public ResponseEntity<List<CrewDTO>> getAllCrew() {
        List<CrewDTO> crewDTOs = crewService.findAllCrew().stream().map(CrewDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(crewDTOs);
    }

    @RequestMapping(value = "/crews/{id}", method = RequestMethod.GET)
    public ResponseEntity<CrewDTO> getCrew(@PathVariable("id") Long id) {
        CrewDTO crewDTO = new CrewDTO(crewService.findCrewById(id).orElse(null));
        return ResponseEntity.ok(crewDTO);
    }

    @RequestMapping(value = "/crews/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CrewDTO> deleteCrew(@PathVariable("id") Long id) {
        crewService.deleteCrew(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/crews/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CrewDTO> updateCrew(@RequestBody Crew crew, @PathVariable Long id) {
        Crew oldCrew = crewService.findCrewById(id).orElse(null);
        if (oldCrew == null) {
            return ResponseEntity.notFound().build();
        } else {
            crewService.updateCrew(crew);
            CrewDTO crewDTO = new CrewDTO(crew);
            return ResponseEntity.ok(crewDTO);
        }
    }

    @RequestMapping(value = "/addCrew", method = RequestMethod.GET)
    public ResponseEntity<CrewDTO> addCrew(Crew crew) {
        crewService.createCrew(crew);
        CrewDTO crewDTO = new CrewDTO(crew);
        return ResponseEntity.ok(crewDTO);
    }

    @RequestMapping(value = "/crews/{id}/person/{personId}", method = RequestMethod.GET)
    public ResponseEntity<CrewDTO> addPersonToCrew(@PathVariable("id") Long id,
                                                @PathVariable("personId") Long personId) {
        Crew crew = crewService.findCrewById(id).orElse(null);
        Person person = personService.findPersonById(personId).orElse(null);
        if (crew == null || person == null) {
            return ResponseEntity.notFound().build();
        } else {
            crew.addPerson(person);
            person.setCrew(crew);
            crewService.updateCrew(crew);
            CrewDTO crewDTO = new CrewDTO(crew);
            return ResponseEntity.ok(crewDTO);
        }
    }

    @RequestMapping(value = "/crews/{id}/transport/{transportId}", method = RequestMethod.GET)
    public ResponseEntity<CrewDTO> addTransportToCrew(@PathVariable("id") Long id,
                                                @PathVariable("transportId") Long transportId) {
        Crew crew = crewService.findCrewById(id).orElse(null);
        Transport transport = transportService.findTransportById(transportId).orElse(null);
        if (crew == null || transport == null) {
            return ResponseEntity.notFound().build();
        } else {
            crew.addTransport(transport);
            transport.setCrew(crew);
            crewService.updateCrew(crew);
            CrewDTO crewDTO = new CrewDTO(crew);
            return ResponseEntity.ok(crewDTO);
        }
    }

    @RequestMapping(value = "/crews/{id}/flight/{flightId}", method = RequestMethod.GET)
    public ResponseEntity<CrewDTO> addFlightToCrew(@PathVariable("id") Long id,
                                                   @PathVariable("flightId") Long flightId) {
        Crew crew = crewService.findCrewById(id).orElse(null);
        Flight flight = flightService.findFlightById(flightId).orElse(null);
        if (crew == null || flight == null) {
            return ResponseEntity.notFound().build();
        } else {
            crew.setFlight(flight);
            flight.setCrew(crew);
            crewService.updateCrew(crew);
            CrewDTO crewDTO = new CrewDTO(crew);
            return ResponseEntity.ok(crewDTO);
        }
    }
}
