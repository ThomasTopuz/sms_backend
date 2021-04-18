package ch.thomastopuz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "schoolClasses"}) // to avoid infinite json recursion
public class Student extends Person {

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "students")
    private List<SchoolClass> schoolClasses = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(String name, String surname, String email, LocalDate dob) {
        super(name, surname, email, dob);
    }

    public Student(Long id, String name, String surname, String email, LocalDate dob) {
        super(id, name, surname, email, dob);
    }

    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }
}
