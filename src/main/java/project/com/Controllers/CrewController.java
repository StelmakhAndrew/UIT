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

/**
 * Єкіпаж контроллер
 * Клас який містить методи для керуванням об'єктами "єкіпаж".
 */
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

    /**
     * Метод який дістає всі єкіпажі, які містяться в базі даних
     * @return список всіх єкіпажів в форматі Json
     */
    @RequestMapping(value = "/crews", method = RequestMethod.GET)
    public ResponseEntity<List<CrewDTO>> getAllCrew() {
        List<CrewDTO> crewDTOs = crewService.findAllCrew().stream().map(CrewDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(crewDTOs);
    }

    /**
     * @param id
     * Метод для отримання єкіпажу по його параметру id
     * @return Об'єкт "екіпаж" в форматі Json
     */
    @RequestMapping(value = "/crews/{id}", method = RequestMethod.GET)
    public ResponseEntity<CrewDTO> getCrew(@PathVariable("id") Long id) {
        Crew crew = crewService.findCrewById(id);
        if (crew == null) return ResponseEntity.notFound().build();
        CrewDTO crewDTO = new CrewDTO(crew);
        return ResponseEntity.ok(crewDTO);
    }

    /**
     * @param id
     * Метод для видалення єкіпажу по id з бази даних
     * @return статус
     */
    @RequestMapping(value = "/crews/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CrewDTO> deleteCrew(@PathVariable("id") Long id) {
        Crew crew = crewService.findCrewById(id);
        if (crew == null) return ResponseEntity.notFound().build();

        crewService.deleteCrew(id);
        return ResponseEntity.ok().build();
    }

    /**
     * @param crew - оновлені дані про екіпаж в форматі Json
     * @param id
     * Метод для оновлення даних єкіпажу.
     * @return оновлене значення екіпажу.
     */
    @RequestMapping(value = "/crews/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CrewDTO> updateCrew(@RequestBody Crew crew, @PathVariable Long id) {
        Crew oldCrew = crewService.findCrewById(id);
        if (oldCrew == null) {
            return ResponseEntity.notFound().build();
        } else {
            crewService.updateCrew(crew);
            CrewDTO crewDTO = new CrewDTO(crew);
            return ResponseEntity.ok(crewDTO);
        }
    }

    /**
     * @param crew - дані екіпажу в форматі Json
     * Метод для додавання екіпажу в базу даних.
     * @return створений екіпаж
     */
    @RequestMapping(value = "/addCrew", method = RequestMethod.POST)
    public ResponseEntity<CrewDTO> addCrew(@RequestBody Crew crew) {
        if (crew == null) return ResponseEntity.noContent().build();
        crewService.createCrew(crew);
        CrewDTO crewDTO = new CrewDTO(crew);
        return ResponseEntity.ok(crewDTO);
    }

    /**
     * @param personId - id існуючої персони
     * @param id - id поточного екіпажу
     * Метод для пов'язування існуючої персони з даним екіпажем.
     * @return поточний екіпаж з позначеним зв'язком з персоною.
     */
    @RequestMapping(value = "/crews/{id}/person/{personId}", method = RequestMethod.PUT)
    public ResponseEntity<CrewDTO> addPersonToCrew(@PathVariable("id") Long id,
                                                @PathVariable("personId") Long personId) {
        Crew crew = crewService.findCrewById(id);
        Person person = personService.findPersonById(personId);
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

    /**
     * @param transportId - id існуючого транспорту
     * @param id - id поточного клієнта
     * Метод для пов'язування існуючого транспорту з даним клієнтом.
     * @return поточний клієнт з позначеним зв'язком з транспортом.
     */
    @RequestMapping(value = "/crews/{id}/transport/{transportId}", method = RequestMethod.PUT)
    public ResponseEntity<CrewDTO> addTransportToCrew(@PathVariable("id") Long id,
                                                @PathVariable("transportId") Long transportId) {
        Crew crew = crewService.findCrewById(id);
        Transport transport = transportService.findTransportById(transportId);
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

    /**
     * @param flightId - id існуючого маршруту
     * @param id - id поточного екіпажу
     * Метод для пов'язування існуючого маршруту з даним екіпажем.
     * @return поточний екіпаж з позначеним зв'язком з маршрутом.
     */
    @RequestMapping(value = "/crews/{id}/flight/{flightId}", method = RequestMethod.PUT)
    public ResponseEntity<CrewDTO> addFlightToCrew(@PathVariable("id") Long id,
                                                   @PathVariable("flightId") Long flightId) {
        Crew crew = crewService.findCrewById(id);
        Flight flight = flightService.findFlightById(flightId);
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
