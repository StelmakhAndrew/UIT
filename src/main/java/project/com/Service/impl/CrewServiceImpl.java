package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.*;
import project.com.Entity.DTO.CrewDTO;
import project.com.Repository.ClientRepository;
import project.com.Repository.CrewRepository;
import project.com.Service.*;

import java.util.List;
import java.util.Optional;

/**
 * реалізація сервісу
 */
@Service
public class CrewServiceImpl implements CrewService {

    @Autowired
    private CrewRepository crewRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private PersonService personService;


    @Override
    public Crew createCrew(CrewDTO crew) {
        Crew newCrew = new Crew();

        if (crew.getClientId()!=null){
            Client currentClient = clientService.findClientById(crew.getClientId());
            if (currentClient ==null) return null;
            newCrew.setClient(currentClient);
            currentClient.addCrews(newCrew);
        }

        if (crew.getFlightId()!=null){
            Flight currentFlight = flightService.findFlightById(crew.getFlightId());
            if (currentFlight == null) return null;
            newCrew.setFlight(currentFlight);
            currentFlight.setCrew(newCrew);
        }

        List<Long> transports = crew.getTransportId();

        if (transports!=null){
            for (Long transportId: transports) {
                Transport currentTransport = transportService.findTransportById(transportId);
                if (currentTransport == null)
                    return null;
                newCrew.addTransport(currentTransport);
                currentTransport.setCrew(newCrew);
            }
        }

        List<Long> persons = crew.getPersonsId();

        if (persons!=null){
            for (Long personId: persons) {
                Person currentPerson = personService.findPersonById(personId);
                if (currentPerson == null)
                    return null;
                newCrew.addPerson(currentPerson);
                currentPerson.setCrew(newCrew);
            }
        }
        crewRepository.save(newCrew);
        return newCrew;
    }

    @Override
    public void updateCrew(Crew crew) {
        crewRepository.save(crew);
    }

    @Override
    public Crew findCrewById(Long id) {
        return crewRepository.findById(id).orElse(null);
    }

    @Override
    public List<Crew> findAllCrew() {
        return crewRepository.findAll();
    }

    @Override
    public void deleteCrew(Long id) {
        crewRepository.deleteById(id);
    }
}
