package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.DTO.TransportDTO;
import project.com.Entity.Transport;
import project.com.Service.TransportService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Транспорт контроллер
 * Клас який містить методи для керуванням об'єктами "Транспорт".
 */
@Controller
@RequestMapping("/transports")
public class TransportController {

    @Autowired
    private TransportService transportService;

    /**
     * Метод який дістає всі транспорти, які містяться в базі даних
     *
     * @return список всіх транспортів в форматі Json
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<TransportDTO>> getAllTransport() {
        List<TransportDTO> transportsDTO = transportService.findAllTransport().stream().map(TransportDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(transportsDTO);
    }

    /**
     * @param id Метод для отримання транспорту по його параметру id
     * @return Об'єкт "транспорт" в форматі Json
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TransportDTO> getTransport(@PathVariable("id") Long id) {
        Transport transport = transportService.findTransportById(id);
        if (transport == null) return ResponseEntity.notFound().build();
        TransportDTO transportDTO = new TransportDTO(transport);
        return ResponseEntity.ok(transportDTO);
    }

    /**
     * @param id Метод для видалення транспорту по id з бази даних
     * @return статус
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTransports(@PathVariable("id") Long id) {
        Transport transport = transportService.findTransportById(id);
        if (transport == null) return ResponseEntity.notFound().build();
        transportService.deleteTransport(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param transport - оновлені дані про транспорт в форматі Json
     * @param id        Метод для оновлення даних транспорту.
     * @return оновлене значення транспорту.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TransportDTO> updateTransports(@RequestBody TransportDTO transport, @PathVariable Long id) {
        Transport oldTransport = transportService.findTransportById(id);

        if (oldTransport == null)
            return ResponseEntity.notFound().build();

        transport.setId(id);
        Transport updateTransport = transportService.updateTransport(transport);

        if (updateTransport == null) return ResponseEntity.notFound().build();

        transport = new TransportDTO(updateTransport);
        return ResponseEntity.ok(transport);
    }

    /**
     * @param transport - дані транспорту в форматі Json
     *                  Метод для додавання транспорту в базу даних.
     * @return створений транспорт.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<TransportDTO> addPerson(@RequestBody TransportDTO transport) {

        if (transport == null) return ResponseEntity.notFound().build();

        Transport newTransport = transportService.createTransport(transport);

        if (newTransport == null) return ResponseEntity.notFound().build();

        transport = new TransportDTO(newTransport);

        return ResponseEntity.ok(transport);
    }
}
