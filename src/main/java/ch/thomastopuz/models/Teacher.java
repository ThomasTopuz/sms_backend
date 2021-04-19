package ch.thomastopuz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the teacher entity, extends Person
 */
@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "schoolClasses"})
public class Teacher extends Person {

    /**
     * the teacher schoolclasses, one to many bidirectional mapping
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "teacher")
    private List<SchoolClass> schoolClasses = new ArrayList<>();

    /**
     * Teacher default constructor
     */
    public Teacher() {
        super();
    }

    /**
     * Teacher constructor without id
     *
     * @param name    the teacher name
     * @param surname the teacher surname
     * @param email   the teacher email
     * @param dob     the teacher date of birth
     */
    public Teacher(String name, String surname, String email, LocalDate dob) {
        super(name, surname, email, dob);
    }

    /**
     * Teacher constructor with id
     *
     * @param id      the teacher id
     * @param name    the teacher name
     * @param surname the teacher surname
     * @param email   the teacher email
     * @param dob     the teacher date of birth
     */
    public Teacher(Long id, String name, String surname, String email, LocalDate dob) {
        super(id, name, surname, email, dob);
    }


    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }
}
