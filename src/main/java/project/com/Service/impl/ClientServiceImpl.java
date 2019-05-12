package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Entity.DTO.ClientDTO;
import project.com.Entity.Transport;
import project.com.Repository.ClientRepository;
import project.com.Service.ClientService;
import project.com.Service.CrewService;
import project.com.Service.TransportService;

import java.util.List;
import java.util.Optional;

/**
 * реалізація сервісу
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    private CrewService crewService;


    @Autowired
    private TransportService transportService;

    @Override
    public Client createClient(ClientDTO client) {
        Client newClient = new Client();
        newClient.setName(client.getName());
        List<Long> crews = client.getCrewsId();
        List<Long> transports = client.getTransportsId();
        if (crews != null) {
            for (Long crew : crews) {
                Crew currentCrew = crewService.findCrewById(crew);
                if (currentCrew == null)
                    return null;
                newClient.addCrews(currentCrew);
                currentCrew.setClient(newClient);
            }
        }
        if (transports != null) {
            for (Long transport : transports) {
                Transport currentTransport = transportService.findTransportById(transport);
                if (currentTransport == null)
                    return null;
                newClient.addTransport(currentTransport);
                currentTransport.setClient(newClient);
            }
        }
        clientRepository.save(newClient);
        return newClient;
    }


    @Override
    public Client updateClient(ClientDTO client) {
        return createClient(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> findAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
