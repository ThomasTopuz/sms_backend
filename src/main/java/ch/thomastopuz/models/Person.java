package ch.thomastopuz.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * Person abstract class, generalization, this class is not a table, it's a generalization
 */
@MappedSuperclass
public abstract class Person {
    /**
     * The person id generated automatically
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The person name
     */
    private String name;

    /**
     * The person surname
     */
    private String surname;

    /**
     * The person email address
     */
    private String email;

    /**
     * The person date of birth
     */
    private final LocalDate dob; // date of birth

    /**
     * The person age, is not a column, is calculate in the getter
     */
    @Transient // not a column
    private int age;

    /**
     * Person default constructor
     */
    public Person() {
        this("defaultName", "defaultSurname", "defaultEmail", LocalDate.now());
    }

    /**
     * Person constructor with id
     *
     * @param id      The person id
     * @param name    the person name
     * @param surname the person surname
     * @param email   the person email
     * @param dob     the person date of birth
     */
    public Person(Long id, String name, String surname, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dob = dob;
    }

    /**
     * Person constructor without id
     *
     * @param name    the person name
     * @param surname the person surname
     * @param email   the person email
     * @param dob     the person date of birth
     */
    public Person(String name, String surname, String email, LocalDate dob) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dob = dob;
    }


    /**
     * person id getter
     *
     * @return the person id
     */
    public Long getId() {
        return id;
    }

    /**
     * person name getter
     *
     * @return the person name
     */
    public String getName() {
        return name;
    }

    /**
     * person name setter
     *
     * @param name the new person name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * person surname getter
     *
     * @return the person surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * person surname setter
     *
     * @param surname the new person surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * person email getter
     *
     * @return the person email
     */
    public String getEmail() {
        return email;
    }

    /**
     * person email setter
     *
     * @param email the new person email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * person date of birth getter
     *
     * @return the person dob
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * person age getter, calculates the person's age based on dob and the current data
     *
     * @return the person age
     */
    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    /**
     * Person tostring method
     *
     * @return the formatted Person string, showing its properties
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
