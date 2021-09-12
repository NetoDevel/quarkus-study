package org.acme.getting.started.resource;

vmport io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.acme.getting.started.entity.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@Transactional
class PersonResourceTest {

    Person person;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.name = "netodevel";
        person.persist();

        var personTwo = new Person();
        personTwo.name = "jose";
        personTwo.persist();
    }

    @Test
    @DisplayName("should return all users ordering by name")
    public void shouldReturnPersons() {
        RestAssured.given()
                .get("/persons").then()
                .body("[0].name", equalTo("jose"))
                .body("[1].name", equalTo("netodevel"));
    }

    @Test
    @DisplayName("should save a person")
    public void shouldSavePerson() {
        String body = "{\n" +
                "    \"name\": \"Jose Neto\",\n" +
                "    \"birth\": \"1990-01-01\"\n" +
                "}";

        RestAssured.given().body(body)
                .contentType("application/json").post("/persons").then()
                .body("name", equalTo("Jose Neto"))
                .body("birth", equalTo("1990-01-01"));
    }

    @Test
    @DisplayName("give a invalid person should return bad request")
    public void shouldReturnBadRequest() {
        var invalidPersonBody = "{\n" +
                "    \"birth\": \"1990-01-01\"\n" +
                "}";

        RestAssured.given().body(invalidPersonBody)
                .contentType("application/json").post("/persons")
                .then().statusCode(400);
    }

    @Test
    @DisplayName("give a person id should return data of person")
    public void shouldReturnDataByPersonId() {
        RestAssured.given()
                .get("/persons/" + person.id).then()
                .body("name", equalTo("netodevel"));
    }

    @Test
    @DisplayName("give a invalid person_id should return no_content")
    public void shouldReturnNoContent() {
        RestAssured.given()
                .get("/persons/123123").then().statusCode(204);
    }

    @Test
    @DisplayName("give a data of person should update data of person")
    public void shouldUpdatePerson() {
        var bodyToUpdate = "{\n" +
                "    \"name\": \"netodevel_updated\",\n" +
                "    \"birth\": \"1980-01-01\"\n" +
                "}";

        RestAssured.given()
                .body(bodyToUpdate)
                .contentType("application/json")
                .put("/persons/" + person.id).then()
                .body("name", equalTo("netodevel_updated"))
                .body("birth", equalTo("1980-01-01"));
    }

    @Test
    @DisplayName("given a invalid person_id to update should return no_content")
    public void shouldReturnNoContentWhenUpdate() {
        var bodyToUpdate = "{\n" +
                "    \"name\": \"netodevel_updated\",\n" +
                "    \"birth\": \"1980-01-01\"\n" +
                "}";

        RestAssured.given()
                .body(bodyToUpdate)
                .contentType("application/json")
                .put("/persons/123123").then().statusCode(204);
    }

    @Test
    @DisplayName("given a valid person_id should return ok")
    public void shouldDeletePerson() {
        RestAssured.given()
                .contentType("application/json")
                .delete("/persons/" + person.id).then().statusCode(200);
    }

    @Test
    @DisplayName("given a invalid person_id should return no_content")
    public void shouldNotDeletePerson() {
        RestAssured.given()
                .contentType("application/json")
                .delete("/persons/1231").then().statusCode(204);
    }

    @AfterEach
    public void tearDown() {
        Person.deleteAll();
    }

}
