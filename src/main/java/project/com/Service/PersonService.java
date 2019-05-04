package project.com.Service;

import org.springframework.stereotype.Service;
import project.com.Entity.Flight;
import project.com.Entity.Person;

import java.util.List;
import java.util.Optional;

/**
 * сервіс для персони
 */
@Service
public interface PersonService {

    void createPerson(Person person);

    void updatePerson(Person person);

    Person findPersonById(Long id);

    List<Person> findAllPerson();

    void deletePerson(Long id);

}