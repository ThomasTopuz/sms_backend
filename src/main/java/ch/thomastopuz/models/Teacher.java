package ch.thomastopuz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "schoolClasses"})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String surname;

    private String email;

    private LocalDate dob; // date of birth

    @Transient // not a column
    private int age;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "teacher")
    private List<SchoolClass> schoolClasses = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(long id, String name, String surname, String email, LocalDate dob, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public Teacher(String name, String surname, String email, LocalDate dob, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }

    public void setSchoolClasses(List<SchoolClass> schoolClasses) {
        this.schoolClasses = schoolClasses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
