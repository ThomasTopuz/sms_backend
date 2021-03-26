package ch.thomastopuz.services;

import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.repositories.SchoolClassRepository;
import ch.thomastopuz.repositories.StudentRepository;
import ch.thomastopuz.utils.AsyncOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;
    SchoolClassRepository schoolClassRepository;
    SchoolClassService schoolClassService;
    AsyncOperation asyncOperation;

    @Autowired
    public StudentService(StudentRepository studentRepository, SchoolClassRepository classRepository,
                          SchoolClassService schoolClassService, AsyncOperation asyncOperation) {
        this.studentRepository = studentRepository;
        this.schoolClassRepository = classRepository;
        this.schoolClassService = schoolClassService;
        this.asyncOperation = asyncOperation;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public List<SchoolClass> getSchoolClasses(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) return null;
        return student.get().getSchoolClasses();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student setStudent(Long studentId, Student newStudent) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) return null;
        student.get().setName(newStudent.getName());
        student.get().setSurname(newStudent.getSurname());
        student.get().setEmail(newStudent.getEmail());
        return student.get();
    }

    @Transactional
    public Student deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) return null;
        // detach all associations before removing
        removeAssociations(id);
        asyncOperation.await(() -> studentRepository.deleteById(id), 1000);// async operation
        return student.get();
    }

    private void removeAssociations(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            for (SchoolClass schoolClass : student.get().getSchoolClasses()) {
                schoolClassService.removeStudent(id, schoolClass.getId());
            }
        }
    }

}
