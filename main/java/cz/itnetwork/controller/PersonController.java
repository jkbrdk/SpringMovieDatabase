package cz.itnetwork.controller;

import cz.itnetwork.constant.RoleType;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @Secured("ROLE_ADMIN")
    @PostMapping({"/people", "/people/"})
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO){
        return personService.addPerson(personDTO);
    }

    @GetMapping(value = {"/actors", "/actors/"})
    public List<PersonDTO> getActors(@RequestParam(required = false, defaultValue = Integer.MAX_VALUE+"") int limit ) {
        return personService.getPeople(RoleType.actor, limit);
    }

    @GetMapping(value = {"/directors", "/directors/"})
    public List<PersonDTO> getDirectors(@RequestParam(required = false, defaultValue = Integer.MAX_VALUE+"") int limit) {
        return personService.getPeople(RoleType.director, limit);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping({"/people/{personId}", "/people/{personId}"})
    public PersonDTO editPerson(@PathVariable Long personId, @RequestBody PersonDTO personDTO) {
        return personService.editPerson(personId, personDTO);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping({"/people/{personId}", "/people/{personId}/"})
    public PersonDTO deletePerson(@PathVariable Long personId) {
        return personService.removePerson(personId);
    }

    @GetMapping({"/people/{personId}", "/people/{personId}/"})
    public PersonDTO getPerson(@PathVariable Long personId) {
        return personService.getPerson(personId);
    }
}
