package br.com.netodevel.demo.resource;

import br.com.netodevel.demo.service.PersonService;
import br.com.netodevel.demo.entity.Person;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    private PersonService personService;

    @GET
    public Response findAll() {
        return ok(personService.listAll()).build();
    }

    @POST
    public Response create(Person person) {
        return ok(personService.create(person)).status(201).build();
    }

    @GET
    @Path(value = "/{id}")
    public Response findById(@PathParam("id") Long id) {
        return ok(personService.findById(id)).build();
    }

    @PUT
    @Path(value = "/{id}")
    public Response update(@PathParam("id") Long id, Person person) {
        return ok(personService.updatePerson(id, person)).build();
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam("id") Long id) {
        return ok(personService.deletePerson(id)).build();
    }
}
