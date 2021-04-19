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

/**
 * Student service layer, provides methods to manipulate the student entity
 */
@Service
public class StudentService {
    /**
     * student JPA repository, is an interface to the database, provided by spring boot dependency injection engine
     */
    StudentRepository studentRepository;
    /**
     * schoolclass service,  provides utility methods for the school class entity, provided by spring boot dependency injection engine
     */
    SchoolClassService schoolClassService;
    /**
     * utility class to throw api exception, provided by spring boot dependency injection engine
     */
    ApiExceptionThrower apiExceptionThrower;
    /**
     * utility class to execute functions after a given delay, is an interface to the database, provided by spring boot dependency injection engine
     */
    AsyncOperation asyncOperation;

    /**
     * student service constructor
     *
     * @param studentRepository   student JPA repository
     * @param schoolClassService  schoolclass service,  provides utility methods for the school class entity
     * @param apiExceptionThrower utility class to throw api exception
     * @param asyncOperation      utility class to execute functions after a given delay
     */
    @Autowired
    public StudentService(StudentRepository studentRepository,
                          @Lazy SchoolClassService schoolClassService, ApiExceptionThrower apiExceptionThrower, AsyncOperation asyncOperation) {
        this.studentRepository = studentRepository;
        this.schoolClassService = schoolClassService;
        this.apiExceptionThrower = apiExceptionThrower;
        this.asyncOperation = asyncOperation;
    }

    /**
     * method to get all students from database
     *
     * @return student list
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * method to get a student given the id
     *
     * @param id the student id
     * @return the student retrieved, if not found throws a not found api exception
     */
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) apiExceptionThrower.throwNotFoundException("student", id);
        return student.get();
    }

    /**
     * method to get a student schoolclasses
     *
     * @param id the student id
     * @return the student's schoolclasses, if student is not found throws a not found api exception
     */
    public List<SchoolClass> getSchoolClasses(Long id) {
        Student student = getStudentById(id);
        return student.getSchoolClasses();
    }

    /**
     * method to create a new student
     *
     * @param student new student payload (createDTO)
     * @return the created student, if missing properties throws a bad request exception
     */
    public Student createStudent(PersonCreateDto student) {
        if (!student.isValidForPOST())
            apiExceptionThrower.throwBadRequestException("Bad request, missing certain property!");
        return studentRepository.save(new Student(student.getName(), student.getSurname(), student.getEmail(), student.getDob()));
    }

    /**
     * method to modify an existing student
     *
     * @param studentId  the student id
     * @param newStudent the new student payload (updateDTO)
     * @return the modifies student, if not found throws a not found api exception
     */
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

    /**
     * method to delete an existing student
     *
     * @param id the student id
     * @return the deleted student, if not found throws a not found api exception
     */
    @Transactional
    public Student deleteStudent(Long id) {
        Student student = getStudentById(id);
        // detach all associations before removing
        removeAssociations(id);
        asyncOperation.await(() -> studentRepository.deleteById(id), 1000);// async operation
        return student;
    }

    /**
     * method to clear associations to safely delete a student
     *
     * @param id the student to remove associations for
     */
    private void removeAssociations(Long id) {
        Student student = getStudentById(id);
        for (SchoolClass schoolClass : student.getSchoolClasses()) {
            schoolClassService.removeStudent(id, schoolClass.getId());
        }
    }

}
