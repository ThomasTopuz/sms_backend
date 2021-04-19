package ch.thomastopuz.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the schoolclass entity
 */
@Entity
@Table
public class SchoolClass {
    /**
     * The scholclass generated id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The schoolclass name
     */
    private String name;

    /**
     * The schoolclass list of students, bidirectional many to many mapping
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Student> students;

    /**
     * The schoolclass teacher, bidirectional one to may mapping
     */
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    /**
     * Schoolclass default constructor
     */
    public SchoolClass() {
    }

    /**
     * Schoolclass constructor with id
     *
     * @param id      schoolclass id
     * @param name    schoolclass name
     * @param teacher schoolclass teacher
     */
    public SchoolClass(Long id, String name, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    /**
     * Schoolclass constructor without id
     *
     * @param name    schoolclass name
     * @param teacher schoolclass teacher
     */
    public SchoolClass(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    /**
     * Method to add a student to a schoolclass, (in the students adds this to his schooclasses)
     *
     * @param s the student to add
     */
    public void addStudent(Student s) {
        students.add(s);
    }

    /**
     * Method to remove a student from a schoolclass, (in the students removes this from his schooclasses)
     *
     * @param s the student to remove
     */
    public void removeStudent(Student s) {
        students.remove(s);
    }

    /**
     * The schoolclass teacher getter
     *
     * @return the schoolclass teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * The scholclass teacher setter
     *
     * @param teacher the new schoolclass teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * the schoolclass id getter
     *
     * @return the schoolclass id
     */
    public Long getId() {
        return id;
    }

    /**
     * the schoolclass name getter
     *
     * @return the schoolclass name
     */
    public String getName() {
        return name;
    }

    /**
     * the schoolclass name setter
     *
     * @param name the new school class name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * the schoolclass students getter
     *
     * @return the schoolclass students list
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * the schoolclass students setter
     *
     * @param students the new students list
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * schoolclass tostring method
     *
     * @return the formatted schoolclass string showing its properties
     */
    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
