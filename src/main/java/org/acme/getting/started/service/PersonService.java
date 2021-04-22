package org.acme.getting.started.service;

import org.acme.getting.started.entity.Person;
import org.acme.getting.started.repository.PersonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    public PersonService(PersonRepository repository) {
        this.personRepository = repository;
    }

    public List<Person> listAll() {
        return personRepository.findOrderByName();
    }

    @Transactional
    public Person create(Person person) {
        personRepository.persist(person);
        return personRepository.findById(person.id);
    }

}
