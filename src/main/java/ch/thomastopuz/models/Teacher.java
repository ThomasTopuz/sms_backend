package ch.thomastopuz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "schoolClasses"})
public class Teacher extends Person {

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "teacher")
    private List<SchoolClass> schoolClasses = new ArrayList<>();

    public Teacher() {
        super();
    }

    public Teacher(String name, String surname, String email, LocalDate dob) {
        super(name, surname, email, dob);
    }

    public Teacher(Long id, String name, String surname, String email, LocalDate dob) {
        super(id, name, surname, email, dob);
    }


    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }
}
