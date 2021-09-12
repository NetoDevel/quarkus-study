package org.acme.getting.started.repository;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.getting.started.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;

@QuarkusTestResource(H2DatabaseTestResource.class)
@QuarkusTest
@Transactional
class PersonRepositoryTest {

    @Inject
    PersonRepository personRepository;

    @Test
    public void testPanacheRepositoryMock() {
        Assertions.assertEquals(0, personRepository.count());

        Person person = new Person(); person.name = "test-xpto";
        person.birth = LocalDate.now();
        person.persist();

        Assertions.assertEquals(1, personRepository.count());
    }

    @BeforeEach
    public void tearDown() {
        personRepository.deleteAll();
    }
}