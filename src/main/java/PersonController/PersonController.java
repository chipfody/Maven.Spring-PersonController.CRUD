package PersonController;

import PersonRepository.PersonRepository;
import io.zipcoder.crudapp.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

     PersonRepository personRepository;

    @Autowired
     public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping ("/people")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return new ResponseEntity<>( personRepository.findOne ( id ), HttpStatus.CREATED);
    }

    @GetMapping ("/people")
    public ResponseEntity <Iterable<Person>> getPersonList(){
        return new ResponseEntity<> (personRepository.findAll (), HttpStatus.CREATED);
    }

    @PutMapping ("/people")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, Long id) {
        if (person.getId () != null) {
            return new ResponseEntity<> (personRepository.save ( person ), HttpStatus.OK);
        } else {
            return new ResponseEntity<> ( createPerson ( person ), HttpStatus.CREATED );
        }
    }

    @DeleteMapping ("/people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Long id) {
        personRepository.delete ( id );
        return new ResponseEntity<> ( true, HttpStatus.OK);

    }
}
