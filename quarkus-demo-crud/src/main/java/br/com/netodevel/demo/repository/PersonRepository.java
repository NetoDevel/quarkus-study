package br.com.netodevel.demo.repository;

import br.com.netodevel.demo.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public List<Person> findOrderByName() {
        return find("order by name").list();
    }

}
