package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.DTO.ClientDTO;
import project.com.Entity.Transport;

import java.util.List;
import java.util.Optional;

/**
 * сервіс для клієнта
 */
@Service
public interface ClientService {

    Client createClient(ClientDTO client);

    void updateClient(Client client);

    Client findClientById(Long id);

    List<Client> findAllClient();

    void deleteClient(Long id);
}