package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Entity.DTO.ClientDTO;
import project.com.Entity.Transport;
import project.com.Service.ClientService;
import project.com.Service.CrewService;
import project.com.Service.TransportService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CrewService crewService;

    @Autowired
    private TransportService transportService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> getAllClient() {
        List<ClientDTO> clientsDTO = clientService.findAllClient().stream().map(ClientDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(clientsDTO);
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> getClient(@PathVariable("id") Long id) {
        ClientDTO clientDTO = new ClientDTO(clientService.findClientById(id).orElse(null));
        return ResponseEntity.ok(clientDTO);
    }

    @RequestMapping(value = "/clients/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/clients/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> updateClient(@RequestBody Client client, @PathVariable Long id) {
        Client oldClient = clientService.findClientById(id).orElse(null);
        if (oldClient == null) {
            return ResponseEntity.notFound().build();
        } else {
            clientService.updateClient(client);
            ClientDTO clientDTO = new ClientDTO(client);
            return ResponseEntity.ok(clientDTO);
        }
    }

    @RequestMapping(value = "/addClient", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> addClient(Client client) {
        clientService.createClient(client);
        ClientDTO clientDTO = new ClientDTO(client);
        return ResponseEntity.ok(clientDTO);
    }

    @RequestMapping(value = "/clients/{id}/crew/{crewId}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> addCrewToClient(@PathVariable Long crewId, @PathVariable Long id) {
        Client client = clientService.findClientById(id).orElse(null);
        Crew crew = crewService.findCrewById(crewId).orElse(null);
        if (client == null || crew == null) {
            return ResponseEntity.notFound().build();
        } else {
            client.addCrews(crew);
            crew.setClient(client);
            clientService.updateClient(client);
            ClientDTO clientDTO = new ClientDTO(client);
            return ResponseEntity.ok(clientDTO);
        }
    }

    @RequestMapping(value = "/clients/{id}/transport/{transportId}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> addTransportToClient(@PathVariable Long transportId, @PathVariable Long id) {
        Client client = clientService.findClientById(id).orElse(null);
        Transport transport = transportService.findTransportById(transportId).orElse(null);
        if (client == null || transport == null) {
            return ResponseEntity.notFound().build();
        } else {
            client.addTransport(transport);
            transport.setClient(client);
            clientService.updateClient(client);
            ClientDTO clientDTO = new ClientDTO(client);
            return ResponseEntity.ok(clientDTO);
        }
    }
}
