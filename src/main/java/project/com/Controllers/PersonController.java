package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.DTO.PersonDTO;
import project.com.Entity.Person;
import project.com.Service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Персона контроллер
 * Клас який містить методи для керуванням об'єктами "Персона".
 */
@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * Метод який дістає всі персони, які містяться в базі даних
     * @return список всіх персон в форматі Json
     */
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<List<PersonDTO>> getAllPerson() {
        List<PersonDTO> personDTOS = personService.findAllPerson().stream().map(PersonDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(personDTOS);
    }

    /**
     * @param id
     * Метод для отримання персони по її параметру id
     * @return Об'єкт "персона" в форматі Json
     */
    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Long id) {

        Person person = personService.findPersonById(id);
        if (person == null) return ResponseEntity.notFound().build();
        PersonDTO personDTO = new PersonDTO(person);
        return ResponseEntity.ok(personDTO);
    }

    /**
     * @param id
     * Метод для видалення персони по id з бази даних
     * @return статус
     */
    @RequestMapping(value = "/persons/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable("id") Long id) {
        Person person = personService.findPersonById(id);
        if (person == null) return ResponseEntity.notFound().build();
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param person - оновлені дані про персону в форматі Json
     * @param id
     * Метод для оновлення даних персони.
     * @return оновлене значення персони.
     */
    @RequestMapping(value = "/persons/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PersonDTO> update(@RequestBody Person person, @PathVariable Long id) {
        Person oldPerson = personService.findPersonById(id);
        if (oldPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            personService.updatePerson(person);
            PersonDTO personDTO = new PersonDTO(person);
            return ResponseEntity.ok(personDTO);
        }
    }

    /**
     * @param person - дані персони в форматі Json
     * Метод для додавання персони в базу даних.
     * @return створена персона.
     */
    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public ResponseEntity<PersonDTO> addPerson(@RequestBody Person person) {
        personService.createPerson(person);
        PersonDTO personDTO = new PersonDTO(person);
        return ResponseEntity.ok(personDTO);
    }
}
