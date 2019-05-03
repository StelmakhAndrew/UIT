package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Person;
import project.com.Service.PersonService;

import java.util.List;


@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPerson() {
        List<Person> persons = personService.findAllPerson();
        return ResponseEntity.ok(persons);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable("id") Long id) {
        Person person = personService.findPersonById(id).orElse(null);
        return ResponseEntity.ok(person);
    }

    @RequestMapping(value = "/persons/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/persons/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> update(@RequestBody Person person, @PathVariable Long id) {
        Person oldPerson = personService.findPersonById(id).orElse(null);
        if (oldPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            personService.updatePerson(person);
            return ResponseEntity.ok(person);
        }
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        personService.createPerson(person);
        return ResponseEntity.ok(person);
    }
}
