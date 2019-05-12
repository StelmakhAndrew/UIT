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

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Клієнт контроллер
 * Клас який містить методи для керуванням об'єктами "клієнт".
 */
@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CrewService crewService;

    @Autowired
    private TransportService transportService;

    /**
     * Метод який дістає всіх клієнтів, які містяться в базі даних
     *
     * @return список всіх клієнтів в форматі Json
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> getAllClient() {
        List<ClientDTO> clientsDTO = clientService.findAllClient().stream().map(ClientDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(clientsDTO);
    }

    /**
     * @param id Метод для отримання клієнта по його параметру id
     * @return Об'єкт "клієнт" в форматі Json
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> getClient(@PathVariable("id") Long id) {
        Client client = clientService.findClientById(id);
        if (client == null)
            return ResponseEntity.notFound().build();
        ClientDTO clientDTO = new ClientDTO(client);
        return ResponseEntity.ok(clientDTO);
    }

    /**
     * @param id Метод для видалення клієнта по id з бази даних
     * @return статус
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable("id") Long id) {
        Client client = clientService.findClientById(id);
        if (client == null)
            return ResponseEntity.notFound().build();
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    /**
     * @param client - оновлені дані про клієнта в форматі Json
     * @param id     Метод для оновлення клієнта
     * @return оновлене значення клієнта.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO client, @PathVariable Long id) {
        Client oldClient = clientService.findClientById(id);
        client.setId(id);

        if (oldClient == null)
            return ResponseEntity.notFound().build();

        Client updateClient = clientService.updateClient(client);

        if (updateClient == null) return ResponseEntity.notFound().build();

        client = new ClientDTO(updateClient);

        return ResponseEntity.ok(client);
    }

    /**
     * @param client - дані клієнта в форматі Json
     *               Метод для додавання клієнта в базу даних.
     * @return створений клієнт
     */
    @RequestMapping(value = "/newclient", method = RequestMethod.POST)
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO client) {

        if (client == null) return ResponseEntity.noContent().build();

        Client newClient = clientService.createClient(client);

        if (newClient == null) {
            return ResponseEntity.badRequest().build();
        }

        client = new ClientDTO(newClient);

        return ResponseEntity.ok(client);
    }

    /**
     * @param crewId   - id існуючого екіпажу
     * @param clientId - id поточного клієнта
     *                 Метод для пов'язування існуючого екіпажу з даним клієнтом.
     * @return поточний клієнт з позначеним зв'язком з екіпажем.
     */
    @RequestMapping(value = "/connectcrew", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> addCrewToClient(@RequestParam(name = "crewId") Long crewId, @RequestParam(name = "clientId") Long clientId) {
        Client client = clientService.findClientById(clientId);
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
     * @param clientId    - id поточного клієнта
     *                    Метод для пов'язування існуючого транспорту з даним клієнтом.
     * @return поточний клієнт з позначеним зв'язком з транспортом.
     */
    @RequestMapping(value = "/connecttransport", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> addTransportToClient(@RequestParam(name = "transportId") Long transportId, @RequestParam(name = "clientId") Long clientId) {
        Client client = clientService.findClientById(clientId);
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
