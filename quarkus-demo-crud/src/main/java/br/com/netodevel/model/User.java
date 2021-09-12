package br.com.netodevel.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User extends PanacheEntity {

    public String name;
    public Integer age;
    public LocalDate createAt;
}