package org.acme.getting.started.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "person")
public class Person extends PanacheEntity {

    @NotBlank(message = "name my not be blank")
    public String name;

    public LocalDate birth;
}
