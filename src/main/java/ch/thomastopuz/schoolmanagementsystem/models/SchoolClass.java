package ch.thomastopuz.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class SchoolClass {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_generator")
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @ManyToMany // one to many mapping
    @Setter
    @Getter
    private List<Student> students;

    public SchoolClass() {
    }


    public void assignClass(Student s) {
        students.add(s);
    }

    public void removeClass(Student s) {
        students.remove(s);
    }
}
