package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Flight;
import project.com.Entity.Person;
import project.com.Repository.FlightRepository;
import project.com.Repository.PersonRepository;
import project.com.Service.FlightService;
import project.com.Service.PersonService;

import java.util.List;
import java.util.Optional;

/**
 * реалізація сервісу
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void createPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void updatePerson(Person person) {
        personRepository.save(person);
    }


    @Override
    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
