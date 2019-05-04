package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.Crew;

import java.util.List;
import java.util.Optional;

/**
 * сервіс для екіпажу
 */
@Service
public interface CrewService {

    void createCrew(Crew crew);


    void updateCrew(Crew crew);

    Crew findCrewById(Long id);

    List<Crew> findAllCrew();

    void deleteCrew(Long id);
}