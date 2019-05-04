package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Entity.Transport;
import project.com.Service.ClientService;
import project.com.Service.CrewService;
import project.com.Service.TransportService;

import java.util.List;


@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CrewService crewService;

    @Autowired
    private TransportService transportService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClient() {
        List<Client> clients = clientService.findAllClient();
        return ResponseEntity.ok(clients);
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
        Client client = clientService.findClientById(id).orElse(null);
        return ResponseEntity.ok(client);
    }

    @RequestMapping(value = "/clients/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/clients/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable Long id) {
        Client oldClient = clientService.findClientById(id).orElse(null);
        if (oldClient == null) {
            return ResponseEntity.notFound().build();
        } else {
            clientService.updateClient(client);
            return ResponseEntity.ok(client);
        }
    }

    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        clientService.createClient(client);
        return ResponseEntity.ok(client);
    }

    @RequestMapping(value = "/clients/{id}/crew/{crewId}", method = RequestMethod.GET)
    public ResponseEntity<Client> addCrewToClient(@PathVariable Long crewId, @PathVariable Long id) {
        Client client = clientService.findClientById(id).orElse(null);
        Crew crew = crewService.findCrewById(crewId).orElse(null);
        if (client == null || crew == null) {
            return ResponseEntity.notFound().build();
        } else {
            client.addCrews(crew);
            crew.setClient(client);
            clientService.updateClient(client);
            return ResponseEntity.ok(client);
        }
    }

    @RequestMapping(value = "/clients/{id}/transport/{transportId}", method = RequestMethod.GET)
    public ResponseEntity<Client> addTransportToClient(@PathVariable Long transportId, @PathVariable Long id) {
        Client client = clientService.findClientById(id).orElse(null);
        Transport transport = transportService.findTransportById(transportId).orElse(null);
        if (client == null || transport == null) {
            return ResponseEntity.notFound().build();
        } else {
            client.addTransport(transport);
            transport.setClient(client);
            clientService.updateClient(client);
            return ResponseEntity.ok(client);
        }
    }
}
