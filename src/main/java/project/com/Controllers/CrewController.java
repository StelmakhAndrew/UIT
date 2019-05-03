package project.com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.Entity.Client;
import project.com.Entity.Crew;
import project.com.Service.CrewService;

import java.util.List;


@Controller
public class CrewController {

    @Autowired
    private CrewService crewService;

    @RequestMapping(value = "/crews", method = RequestMethod.GET)
    public ResponseEntity<List<Crew>> getAllCrew() {
        List<Crew> crew = crewService.findAllCrew();
        return ResponseEntity.ok(crew);
    }

    @RequestMapping(value = "/crews/{id}", method = RequestMethod.GET)
    public ResponseEntity<Crew> getCrew(@PathVariable("id") Long id) {
        Crew crew = crewService.findCrewById(id).orElse(null);
        return ResponseEntity.ok(crew);
    }

    @RequestMapping(value = "/crews/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Crew> deleteCrew(@PathVariable("id") Long id) {
        crewService.deleteCrew(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/crews/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Crew> updateCrew(@RequestBody Crew crew, @PathVariable Long id) {
        Crew oldCrew = crewService.findCrewById(id).orElse(null);
        if (oldCrew == null) {
            return ResponseEntity.notFound().build();
        } else {
            crewService.updateCrew(crew);
            return ResponseEntity.ok(crew);
        }
    }

    @RequestMapping(value = "/addCrew", method = RequestMethod.POST)
    public ResponseEntity<Crew> addCrew(@RequestBody Crew crew) {
        crewService.createCrew(crew);
        return ResponseEntity.ok(crew);
    }
}
