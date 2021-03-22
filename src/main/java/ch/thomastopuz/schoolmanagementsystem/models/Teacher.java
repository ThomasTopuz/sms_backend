package ch.thomastopuz.schoolmanagementsystem.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
public class Teacher extends Person {

    @ManyToMany
    private List<SchoolClass> schoolClasses;

    public Teacher(long id, String name, String surname, String email, LocalDate dob) {
        super(id, name, surname, email, dob);
        initClasses();
    }

    public Teacher(String name, String surname, String email, LocalDate dob) {
        super(name, surname, email, dob);
        initClasses();
    }

    public Teacher() {
    }

    private void initClasses() {
        this.schoolClasses = new ArrayList<>();
    }

    public void assignClass(SchoolClass c) {
        schoolClasses.add(c);
    }

    public void removeClass(SchoolClass c) {
        schoolClasses.remove(c);
    }
}
