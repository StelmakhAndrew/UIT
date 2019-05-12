package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Entity.DTO.TransportDTO;
import project.com.Entity.Transport;
import project.com.Repository.TransportRepository;
import project.com.Service.ClientService;
import project.com.Service.CrewService;
import project.com.Service.TransportService;

import java.util.List;
import java.util.Optional;

/**
 * реалізація сервісу
 */
@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private CrewService crewService;

    @Autowired
    private ClientService clientService;

    @Override
    public Transport createTransport(TransportDTO transport) {
        Transport newTransport = new Transport();

        newTransport.setMileage(transport.getMileage());
        newTransport.setModel(transport.getModel());
        newTransport.setPhotoUrl(transport.getPhotoUrl());
        newTransport.setProductionYear(transport.getProductionYear());

        if (transport.getClientId() != null){
            Client currentClient = clientService.findClientById(transport.getClientId());
            if (currentClient == null) return null;
            newTransport.setClient(currentClient);
            currentClient.addTransport(newTransport);
        }

        if (transport.getCrewId() != null){
            Crew currentCrew = crewService.findCrewById(transport.getCrewId());
            if (currentCrew == null) return null;
            newTransport.setCrew(currentCrew);
            currentCrew.addTransport(newTransport);
        }

        transportRepository.save(newTransport);
        return newTransport;
    }

    @Override
    public void updateTransport(Transport transport) {
        transportRepository.save(transport);
    }

    @Override
    public Transport findTransportById(Long id) {
        return transportRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transport> findAllTransport() {
        return transportRepository.findAll();
    }

    @Override
    public void deleteTransport(Long id) {
        transportRepository.deleteById(id);
    }
}
