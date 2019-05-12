package project.com.Controllers;

import com.sun.org.apache.regexp.internal.RE;
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
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * Метод який дістає всі персони, які містяться в базі даних
     *
     * @return список всіх персон в форматі Json
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<PersonDTO>> getAllPerson() {
        List<PersonDTO> personDTOS = personService.findAllPerson().stream().map(PersonDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(personDTOS);
    }

    /**
     * @param id Метод для отримання персони по її параметру id
     * @return Об'єкт "персона" в форматі Json
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Long id) {

        Person person = personService.findPersonById(id);
        if (person == null) return ResponseEntity.notFound().build();
        PersonDTO personDTO = new PersonDTO(person);
        return ResponseEntity.ok(personDTO);
    }

    /**
     * @param id Метод для видалення персони по id з бази даних
     * @return статус
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable("id") Long id) {
        Person person = personService.findPersonById(id);
        if (person == null) return ResponseEntity.notFound().build();
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param person - оновлені дані про персону в форматі Json
     * @param id     Метод для оновлення даних персони.
     * @return оновлене значення персони.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person, @PathVariable Long id) {
        person.setId(id);
        Person oldPerson = personService.findPersonById(id);

        if (oldPerson == null)
            return ResponseEntity.notFound().build();

        Person updatePerson = personService.updatePerson(person);

        if (updatePerson == null) return ResponseEntity.notFound().build();

        person = new PersonDTO(updatePerson);

        return ResponseEntity.ok(person);
    }

    /**
     * @param person - дані персони в форматі Json
     *               Метод для додавання персони в базу даних.
     * @return створена персона.
     */
    @RequestMapping(value = "/newperson", method = RequestMethod.POST)
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO person) {

        if (person == null) return ResponseEntity.notFound().build();

        Person newPerson = personService.createPerson(person);

        if (newPerson == null) return ResponseEntity.notFound().build();

        person = new PersonDTO(newPerson);

        return ResponseEntity.ok(person);
    }
}
