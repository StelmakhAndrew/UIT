package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.*;
import project.com.Entity.DTO.CrewDTO;
import project.com.Service.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Єкіпаж контроллер
 * Клас який містить методи для керуванням об'єктами "єкіпаж".
 */
@Controller
@RequestMapping("/crews")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @Autowired
    private PersonService personService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private FlightService flightService;


    @Autowired
    private ClientService clientService;

    /**
     * Метод який дістає всі єкіпажі, які містяться в базі даних
     * @return список всіх єкіпажів в форматі Json
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CrewDTO>> getAllCrew() {
        List<CrewDTO> crewDTOs = crewService.findAllCrew().stream().map(CrewDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(crewDTOs);
    }

    /**
     * @param id
     * Метод для отримання єкіпажу по його параметру id
     * @return Об'єкт "екіпаж" в форматі Json
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/newcrew", method = RequestMethod.POST)
    public ResponseEntity<CrewDTO> addCrew(@RequestBody CrewDTO crew) {

        if (crew == null) return ResponseEntity.noContent().build();

        Crew newCrew = crewService.createCrew(crew);

        if (newCrew==null) return ResponseEntity.badRequest().build();

        crew = new CrewDTO(newCrew);

        return ResponseEntity.ok(crew);
    }

    /**
     * @param personId - id існуючої персони
     * @param crewId - id поточного екіпажу
     * Метод для пов'язування існуючої персони з даним екіпажем.
     * @return поточний екіпаж з позначеним зв'язком з персоною.
     */
    @RequestMapping(value = "/connectperson", method = RequestMethod.PUT)
    public ResponseEntity<CrewDTO> addPersonToCrew(@RequestParam("crewId") Long crewId,
                                                @RequestParam("personId") Long personId) {
        Crew crew = crewService.findCrewById(crewId);
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
     * @param crewId - id поточного клієнта
     * Метод для пов'язування існуючого транспорту з даним клієнтом.
     * @return поточний клієнт з позначеним зв'язком з транспортом.
     */
    @RequestMapping(value = "/connecttransport", method = RequestMethod.PUT)
    public ResponseEntity<CrewDTO> addTransportToCrew(@RequestParam("crewId") Long crewId,
                                                @RequestParam("transportId") Long transportId) {
        Crew crew = crewService.findCrewById(crewId);
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
     * @param crewId - id поточного екіпажу
     * Метод для пов'язування існуючого маршруту з даним екіпажем.
     * @return поточний екіпаж з позначеним зв'язком з маршрутом.
     */
    @RequestMapping(value = "/connectflight", method = RequestMethod.PUT)
    public ResponseEntity<CrewDTO> addFlightToCrew(@RequestParam("crewId") Long crewId,
                                                   @RequestParam("flightId") Long flightId) {
        Crew crew = crewService.findCrewById(crewId);
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
