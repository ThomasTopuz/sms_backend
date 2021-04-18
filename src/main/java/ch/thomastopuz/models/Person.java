package ch.thomastopuz.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private final LocalDate dob; // date of birth

    @Transient // not a column
    private int age;

    public Person() {
        this("defaultName", "defaultSurname", "defaultEmail", LocalDate.now());
    }

    public Person(Long id, String name, String surname, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dob = dob;
    }

    public Person(String name, String surname, String email, LocalDate dob) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dob = dob;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
