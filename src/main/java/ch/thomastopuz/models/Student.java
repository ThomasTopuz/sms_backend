package ch.thomastopuz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the Student entity, extends Person abstract class
 */
@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "schoolClasses"}) // to avoid infinite json recursion
public class Student extends Person {

    /**
     * The student's schoolclasses, bidirectional many to many mapping
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "students")
    private List<SchoolClass> schoolClasses = new ArrayList<>();

    /**
     * Student's default constructor
     */
    public Student() {
        super();
    }


    /**
     * Student constructor without id
     *
     * @param name    the student name
     * @param surname the student surname
     * @param email   the student email
     * @param dob     the student date of birth
     */
    public Student(String name, String surname, String email, LocalDate dob) {
        super(name, surname, email, dob);
    }

    /**
     * Student constructor with id
     *
     * @param id      the student id
     * @param name    the student name
     * @param surname the student surname
     * @param email   the student email
     * @param dob     the student date of birth
     */
    public Student(Long id, String name, String surname, String email, LocalDate dob) {
        super(id, name, surname, email, dob);
    }

    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }
}
