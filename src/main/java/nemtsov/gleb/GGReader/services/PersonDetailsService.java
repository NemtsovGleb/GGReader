package nemtsov.gleb.GGReader.services;

import nemtsov.gleb.GGReader.models.Person;
import nemtsov.gleb.GGReader.repositories.PersonRepository;
import nemtsov.gleb.GGReader.security.PersonDetails;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByUsername(username);

        if(person.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return new PersonDetails(person.get());
    }
}
