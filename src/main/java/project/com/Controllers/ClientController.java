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


/**
 * Клієнт контроллер
 * Клас який містить методи для керуванням об'єктами "клієнт".
 */
@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CrewService crewService;

    @Autowired
    private TransportService transportService;

    /**
     * Метод який дістає всіх клієнтів, які містяться в базі даних
     * @return список всіх клієнтів в форматі Json
     */
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> getAllClient() {
        List<ClientDTO> clientsDTO = clientService.findAllClient().stream().map(ClientDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(clientsDTO);
    }

    /**
     * @param id
     * Метод для отримання клієнта по його параметру id
     * @return Об'єкт "клієнт" в форматі Json
     */
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> getClient(@PathVariable("id") Long id) {
        Client client = clientService.findClientById(id);
        if (client == null)
            return ResponseEntity.notFound().build();
        ClientDTO clientDTO = new ClientDTO(client);
        return ResponseEntity.ok(clientDTO);
    }

    /**
     * @param id
     * Метод для видалення клієнта по id з бази даних
     * @return статус
     */
    @RequestMapping(value = "/clients/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable("id") Long id) {
        Client client = clientService.findClientById(id);
        if (client == null)
            return ResponseEntity.notFound().build();
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    /**
     * @param client - оновлені дані про клієнта в форматі Json
     * @param id
     * Метод для оновлення клієнта
     * @return оновлене значення клієнта.
     */
    @RequestMapping(value = "/clients/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> updateClient(@RequestBody Client client, @PathVariable Long id) {
        Client oldClient = clientService.findClientById(id);
        if (oldClient == null) {
            return ResponseEntity.notFound().build();
        } else {
            clientService.updateClient(client);
            ClientDTO clientDTO = new ClientDTO(client);
            return ResponseEntity.ok(clientDTO);
        }
    }

    /**
     * @param client - дані клієнта в форматі Json
     * Метод для додавання клієнта в базу даних.
     * @return створений клієнт
     */
    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public ResponseEntity<ClientDTO> addClient(@RequestBody Client client) {
        if (client == null) return ResponseEntity.noContent().build();
        clientService.createClient(client);
        ClientDTO clientDTO = new ClientDTO(client);
        return ResponseEntity.ok(clientDTO);
    }

    /**
     * @param crewId - id існуючого екіпажу
     * @param id - id поточного клієнта
     * Метод для пов'язування існуючого екіпажу з даним клієнтом.
     * @return поточний клієнт з позначеним зв'язком з екіпажем.
     */
    @RequestMapping(value = "/clients/{id}/crew/{crewId}", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> addCrewToClient(@PathVariable Long crewId, @PathVariable Long id) {
        Client client = clientService.findClientById(id);
        Crew crew = crewService.findCrewById(crewId);
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

    /**
     * @param transportId - id існуючого транспорту
     * @param id - id поточного клієнта
     * Метод для пов'язування існуючого транспорту з даним клієнтом.
     * @return поточний клієнт з позначеним зв'язком з транспортом.
     */
    @RequestMapping(value = "/clients/{id}/transport/{transportId}", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> addTransportToClient(@PathVariable Long transportId, @PathVariable Long id) {
        Client client = clientService.findClientById(id);
        Transport transport = transportService.findTransportById(transportId);
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
