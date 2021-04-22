package org.acme.getting.started.resource;

import io.quarkus.test.junit.QuarkusTest;
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

    @BeforeEach
    public void setUp() {
        Person person = new Person();
        person.name = "netodevel";
        person.persist();

        Person personTwo = new Person();
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

    @AfterEach
    public void tearDown() {
        Person.deleteAll();
    }
}