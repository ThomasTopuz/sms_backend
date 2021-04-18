package ch.thomastopuz.services;

import ch.thomastopuz.exception.ApiExceptionThrower;
import ch.thomastopuz.dto.Person.PersonCreateDto;
import ch.thomastopuz.dto.Person.PersonUpdateDto;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.repositories.StudentRepository;
import ch.thomastopuz.utils.AsyncOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service

public class StudentService {
    StudentRepository studentRepository;
    SchoolClassService schoolClassService;
    ApiExceptionThrower apiExceptionThrower;
    AsyncOperation asyncOperation;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          @Lazy SchoolClassService schoolClassService, ApiExceptionThrower apiExceptionThrower, AsyncOperation asyncOperation) {
        this.studentRepository = studentRepository;
        this.schoolClassService = schoolClassService;
        this.apiExceptionThrower = apiExceptionThrower;
        this.asyncOperation = asyncOperation;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) apiExceptionThrower.throwNotFoundException("student", id);
        return student.get();
    }

    public List<SchoolClass> getSchoolClasses(Long id) {
        Student student = getStudentById(id);
        return student.getSchoolClasses();
    }

    public Student createStudent(PersonCreateDto student) {
        if (!student.isValidForPOST()) apiExceptionThrower.throwBadRequestException("Bad request, missing certain property!");
        return studentRepository.save(new Student(student.getName(), student.getSurname(), student.getEmail(), student.getDob()));
    }

    @Transactional
    public Student setStudent(Long studentId, PersonUpdateDto newStudent) {
        Student student = getStudentById(studentId);
        if (newStudent.getName() != null)
            student.setName(newStudent.getName());
        if (newStudent.getSurname() != null)
            student.setSurname(newStudent.getSurname());
        if (newStudent.getEmail() != null)
            student.setEmail(newStudent.getEmail());
        return student;
    }

    @Transactional
    public Student deleteStudent(Long id) {
        Student student = getStudentById(id);
        // detach all associations before removing
        removeAssociations(id);
        asyncOperation.await(() -> studentRepository.deleteById(id), 1000);// async operation
        return student;
    }

    private void removeAssociations(Long id) {
        Student student = getStudentById(id);
        for (SchoolClass schoolClass : student.getSchoolClasses()) {
            schoolClassService.removeStudent(id, schoolClass.getId());
        }
    }

}
