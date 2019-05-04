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


@Controller
public class TransportController {

    @Autowired
    private TransportService transportService;

    @RequestMapping(value = "/transports", method = RequestMethod.GET)
    public ResponseEntity<List<TransportDTO>> getAllTransport() {
        List<TransportDTO> transportsDTO = transportService.findAllTransport().stream().map(TransportDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(transportsDTO);
    }

    @RequestMapping(value = "/transports/{id}", method = RequestMethod.GET)
    public ResponseEntity<TransportDTO> getTransport(@PathVariable("id") Long id) {
        TransportDTO transportDTO = new TransportDTO(transportService.findTransportById(id).orElse(null));
        return ResponseEntity.ok(transportDTO);
    }

    @RequestMapping(value = "/transports/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTransports(@PathVariable("id") Long id) {
        transportService.deleteTransport(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/transports/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TransportDTO> updateTransports(@RequestBody Transport transport, @PathVariable Long id) {
        Transport oldTransport = transportService.findTransportById(id).orElse(null);
        if (oldTransport == null) {
            return ResponseEntity.notFound().build();
        } else {
            transportService.updateTransport(transport);
            TransportDTO transportDTO = new TransportDTO(transport);
            return ResponseEntity.ok(transportDTO);
        }
    }

    @RequestMapping(value = "/addTransport", method = RequestMethod.GET)
    public ResponseEntity<TransportDTO> addPerson(Transport transport) {
        transportService.createTransport(transport);
        TransportDTO transportDTO = new TransportDTO(transport);
        return ResponseEntity.ok(transportDTO);
    }
}
