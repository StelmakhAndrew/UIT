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

/**
 * Маршрут контроллер
 * Клас який містить методи для керуванням об'єктами "маршрут".
 */
@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    /**
     * Метод який дістає всі маршрути, які містяться в базі даних
     *
     * @return список всіх маршрутів в форматі Json
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<FlightDTO>> getAllFlight() {
        List<FlightDTO> flightsDTO = flightService.findAllFlight().stream().map(FlightDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(flightsDTO);
    }

    /**
     * @param id Метод для отримання маршруту по його параметру id
     * @return Об'єкт "маршрут" в форматі Json
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FlightDTO> getFlight(@PathVariable("id") Long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight == null) return ResponseEntity.notFound().build();
        FlightDTO flightDTO = new FlightDTO(flight);
        return ResponseEntity.ok(flightDTO);
    }

    /**
     * @param id Метод для видалення маршруту по id з бази даних
     * @return статус
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<FlightDTO> deleteFlight(@PathVariable("id") Long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight == null) return ResponseEntity.notFound().build();
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param flight - оновлені дані про маршрут в форматі Json
     * @param id     Метод для оновлення даних маршруту.
     * @return оновлене значення маршруту.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FlightDTO> updateFlight(@RequestBody FlightDTO flight, @PathVariable Long id) {
        Flight oldFlight = flightService.findFlightById(id);
        flight.setId(id);

        if (oldFlight == null) return ResponseEntity.notFound().build();

        Flight updateFlight = flightService.updateFlight(flight);

        if (updateFlight == null) return ResponseEntity.notFound().build();

        flight = new FlightDTO(updateFlight);

        return ResponseEntity.ok(flight);
    }

    /**
     * @param flight - дані маршруту в форматі Json
     *               Метод для додавання маршруту в базу даних.
     * @return створений маршрут
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<FlightDTO> addClient(@RequestBody FlightDTO flight) {

        if (flight == null) return ResponseEntity.noContent().build();

        Flight newFlight = flightService.createFlight(flight);

        if (newFlight == null) return ResponseEntity.badRequest().build();

        flight = new FlightDTO(newFlight);

        return ResponseEntity.ok(flight);
    }
}
