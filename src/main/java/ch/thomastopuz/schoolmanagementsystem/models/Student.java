package ch.thomastopuz.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Student extends Person {

    @ManyToMany
    @Getter
    @Setter
    private List<SchoolClass> schoolClasses;

    public Student(String name, String surname, String email, LocalDate dob) {
        super(name, surname, email, dob);
        initClasses();
    }

    public Student(long id, String name, String surname, String email, LocalDate dob) {
        super(id, name, surname, email, dob);
        initClasses();
    }

    public Student() {
        super();
        initClasses();
    }


    private void initClasses() {
        this.schoolClasses = new ArrayList<>();
    }

    public void assignClass(SchoolClass c) {
        c.assignClass(this);
        schoolClasses.add(c);
    }

    public void removeClass(SchoolClass c) {
        c.removeClass(this);
        schoolClasses.remove(c);
    }
}
