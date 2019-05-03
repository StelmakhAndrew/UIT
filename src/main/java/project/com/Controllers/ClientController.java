package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Client;
import project.com.Service.ClientService;

import java.util.List;


@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

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
}
