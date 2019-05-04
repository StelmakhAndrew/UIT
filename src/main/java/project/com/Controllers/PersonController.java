package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.DTO.PersonDTO;
import project.com.Entity.Person;
import project.com.Service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<List<PersonDTO>> getAllPerson() {
        List<PersonDTO> personDTOS = personService.findAllPerson().stream().map(PersonDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(personDTOS);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Long id) {
        PersonDTO person = new PersonDTO(Objects.requireNonNull(personService.findPersonById(id).orElse(null)));
        return ResponseEntity.ok(person);
    }

    @RequestMapping(value = "/persons/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/persons/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PersonDTO> update(@RequestBody Person person, @PathVariable Long id) {
        Person oldPerson = personService.findPersonById(id).orElse(null);
        if (oldPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            personService.updatePerson(person);
            PersonDTO personDTO = new PersonDTO(person);
            return ResponseEntity.ok(personDTO);
        }
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.GET)
    public ResponseEntity<PersonDTO> addPerson(Person person) {
        personService.createPerson(person);
        PersonDTO personDTO = new PersonDTO(person);
        return ResponseEntity.ok(personDTO);
    }
}
