package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Repository.ClientRepository;
import project.com.Service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void createClient(Client client) {
        clientRepository.save(client);
    }


}
