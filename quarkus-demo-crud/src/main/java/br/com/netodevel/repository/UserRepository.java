package br.com.netodevel.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import br.com.netodevel.model.User;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
