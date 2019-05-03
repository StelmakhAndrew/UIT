package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.Crew;

import java.util.List;
import java.util.Optional;

@Service
public interface CrewService {

    void createCrew(Crew crew);


    void updateCrew(Crew crew);

    Optional<Crew> findCrewById(Long id);

    List<Crew> findAllCrew();

    void deleteCrew(Long id);
}