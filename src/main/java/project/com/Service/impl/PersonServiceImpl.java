package project.com.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.com.Entity.Crew;
import project.com.Entity.DTO.PersonDTO;
import project.com.Entity.Flight;
import project.com.Entity.Person;
import project.com.Repository.FlightRepository;
import project.com.Repository.PersonRepository;
import project.com.Service.CrewService;
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

    @Autowired
    private CrewService crewService;

    @Override
    public Person createPerson(PersonDTO person) {
        Person newPerson = new Person();
        newPerson.setId(person.getId());
        newPerson.setSecondName(person.getSecondName());
        newPerson.setFirstName(person.getFirstName());

        if (person.getCrewId() != null) {
            Crew currentCrew = crewService.findCrewById(person.getCrewId());
            if (currentCrew == null) return null;
            newPerson.setCrew(currentCrew);
            currentCrew.addPerson(newPerson);
        }

        personRepository.save(newPerson);
        return newPerson;
    }

    @Override
    public Person updatePerson(PersonDTO person) {
        return createPerson(person);
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
