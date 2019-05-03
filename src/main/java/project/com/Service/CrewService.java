package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.Crew;

@Service
public interface CrewService {

    void createCrew(Crew crew);
}