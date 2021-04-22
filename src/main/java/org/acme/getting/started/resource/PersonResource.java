package org.acme.getting.started.resource;

import org.acme.getting.started.entity.Person;
import org.acme.getting.started.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.ok;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    private PersonService personService;

    @GET
    public List<Person> listAll() {
        return personService.listAll();
    }

    @GET
    @Path(value = "/v2")
    public Response findAll() {
        return ok(personService.listAll()).build();
    }

    @POST
    public Response create(Person person) {
        return ok(personService.create(person)).status(201).build();
    }
}
