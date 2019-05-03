package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Transport;
import project.com.Service.TransportService;

import java.util.List;


@Controller
public class TransportController {

    @Autowired
    private TransportService transportService;

    @RequestMapping(value = "/transports", method = RequestMethod.GET)
    public ResponseEntity<List<Transport>> getAllTransport() {
        List<Transport> transports = transportService.findAllTransport();
        return ResponseEntity.ok(transports);
    }

    @RequestMapping(value = "/transports/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transport> getTransport(@PathVariable("id") Long id) {
        Transport transport = transportService.findTransportById(id).orElse(null);
        return ResponseEntity.ok(transport);
    }

    @RequestMapping(value = "/transports/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTransports(@RequestParam("id") Long id) {
        transportService.deleteTransport(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/transports/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Transport> updateTransports(@RequestBody Transport transport, @PathVariable Long id) {
        Transport oldTransport = transportService.findTransportById(id).orElse(null);
        if (oldTransport == null) {
            return ResponseEntity.notFound().build();
        } else {
            transportService.updateTransport(transport);
            return ResponseEntity.ok(transport);
        }
    }

    @RequestMapping(value = "/addTransport", method = RequestMethod.POST)
    public ResponseEntity<Transport> addPerson(@RequestBody Transport transport) {
        transportService.createTransport(transport);
        return ResponseEntity.ok(transport);
    }
}
