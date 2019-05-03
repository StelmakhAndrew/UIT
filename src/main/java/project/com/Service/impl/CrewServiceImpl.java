package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Repository.ClientRepository;
import project.com.Repository.CrewRepository;
import project.com.Service.ClientService;
import project.com.Service.CrewService;

@Service
public class CrewServiceImpl implements CrewService {

    @Autowired
    private CrewRepository crewRepository;

    @Override
    public void createCrew(Crew crew) {
        crewRepository.save(crew);
    }
}
