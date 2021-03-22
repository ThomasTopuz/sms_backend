package ch.thomastopuz.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Table
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_generator")
    @Setter
    @Getter
    protected long id;

    @Setter
    @Getter
    protected String name;

    @Setter
    @Getter
    protected String surname;

    @Setter
    @Getter
    protected String email;

    @Setter
    @Getter
    protected LocalDate dob; // date of birth

    @Transient // not a column
    @Setter
    @Getter
    protected int age;

    public Person(long id, String name,String surname, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.surname = surname;
    }

    public Person(String name, String surname, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.surname = surname;
    }

    public Person() {
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
