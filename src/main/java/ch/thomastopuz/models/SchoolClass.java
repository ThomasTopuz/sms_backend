package ch.thomastopuz.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Student> students;

    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    public SchoolClass() {
    }

    public SchoolClass(Long id, String name, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    public SchoolClass(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void removeStudent(Student s) {
        students.remove(s);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
