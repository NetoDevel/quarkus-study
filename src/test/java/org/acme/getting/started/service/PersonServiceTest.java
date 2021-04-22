package org.acme.getting.started.service;

import org.acme.getting.started.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class PersonServiceTest {

    PersonService personService;
    PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        personRepository = mock(PersonRepository.class);
        personService = new PersonService(personRepository);
    }

    @Test
    @DisplayName("should execute query order by name")
    public void shouldInvokeFind() {
        personService.listAll();
        Mockito.verify(personRepository, times(1)).findOrderByName();
    }

}