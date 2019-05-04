package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Repository.ClientRepository;
import project.com.Repository.CrewRepository;
import project.com.Service.ClientService;
import project.com.Service.CrewService;

import java.util.List;
import java.util.Optional;

/**
 * реалізація сервісу
 */
@Service
public class CrewServiceImpl implements CrewService {

    @Autowired
    private CrewRepository crewRepository;

    @Override
    public void createCrew(Crew crew) {
        crewRepository.save(crew);
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
