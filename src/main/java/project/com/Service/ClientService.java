package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.Transport;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {

    void createClient(Client client);

    void updateClient(Client client);

    Optional<Client> findClientById(Long id);

    List<Client> findAllClient();

    void deleteClient(Long id);
}