package org.acme.getting.started.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.getting.started.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public List<Person> findOrderByName() {
        return find("order by name").list();
    }
}
