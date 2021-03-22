package ch.thomastopuz.schoolmanagementsystem.services;

import ch.thomastopuz.schoolmanagementsystem.models.SchoolClass;
import ch.thomastopuz.schoolmanagementsystem.models.Student;
import ch.thomastopuz.schoolmanagementsystem.repositories.ClassRepository;
import ch.thomastopuz.schoolmanagementsystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;
    ClassRepository classRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository, ClassRepository classRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            studentRepository.deleteById(id);
            return student.get();
        } else {
            return null;
        }
    }
    @Transactional // can use custom method, i don't need to write queries
    public Student assignToClass(Long studentId, Long ClassId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<SchoolClass> schoolClass = classRepository.findById(ClassId);
        if (student.isEmpty() || schoolClass.isEmpty()) {
            throw new IllegalStateException("Invalid Student or class");
        } else {
            student.get().assignClass(schoolClass.get());
        }
        return student.get();
    }

}
