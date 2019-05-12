package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Entity.DTO.CrewDTO;

import java.util.List;
import java.util.Optional;

/**
 * сервіс для екіпажу
 */
@Service
public interface CrewService {

    Crew createCrew(CrewDTO crew);

    Crew updateCrew(CrewDTO crew);

    Crew updateCrew(Crew crew);

    Crew findCrewById(Long id);

    List<Crew> findAllCrew();

    void deleteCrew(Long id);
}