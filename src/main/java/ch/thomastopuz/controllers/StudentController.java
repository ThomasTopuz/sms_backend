package ch.thomastopuz.controllers;

import ch.thomastopuz.dto.Person.PersonCreateDto;
import ch.thomastopuz.dto.Person.PersonUpdateDto;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.services.SchoolClassService;
import ch.thomastopuz.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API controller reponsible for the student entity, defines API endpoints and calls service methods
 */
@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    /**
     * student service injected by spring boot, used to call his service methods
     */
    StudentService studentService;

    /**
     * school class service injected by spring boot, used to call his service methods
     */
    SchoolClassService schoolClassService; // dependency injection

    /**
     * Controller constructor (called by spiring boot dependency injection engine)
     *
     * @param studentService     the studentService instance created by spring boot
     * @param schoolClassService the schoolclass service instance  created by spring boot
     */
    @Autowired
    public StudentController(StudentService studentService, SchoolClassService schoolClassService) {
        this.studentService = studentService;
        this.schoolClassService = schoolClassService;
    }

    /**
     * API endpoint to GET all students
     *
     * @return list of students
     */
    @CrossOrigin
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    /**
     * API endpoint to get a student by his id
     *
     * @param id the students id
     * @return returns the student, if not found throws a notFoundException and returns its payload
     */
    @CrossOrigin
    @GetMapping("{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(Long.parseLong(id));
    }

    /**
     * API endpoint to get the student's schoolclasses
     *
     * @param id the student id
     * @return the student's schoolclasses, if not found throws a notFoundException and returns its payload
     */
    @CrossOrigin
    @GetMapping("{id}/schoolclasses")
    public List<SchoolClass> getSchoolClasses(@PathVariable String id) {
        return studentService.getSchoolClasses(Long.parseLong(id));
    }

    /**
     * API endpoint to POST a new student
     *
     * @param student the new student payload
     * @return the crated student, throw a badRequest exception if missing certain properties
     */
    @CrossOrigin
    @PostMapping
    public Student createStudent(@RequestBody PersonCreateDto student) {
        return studentService.createStudent(student);
    }

    /**
     * API endpoint to PUT (modify) an existing student
     *
     * @param id      the student id
     * @param student the new student payload
     * @return the modified student, if not found throws a notFoundException
     */
    @CrossOrigin
    @PutMapping("{id}")
    public Student setStudent(@PathVariable String id, @RequestBody PersonUpdateDto student) {
        return studentService.setStudent(Long.parseLong(id), student);
    }

    /**
     * API endpoint to DELETE a student
     *
     * @param id the student id
     * @return the deleted student, if not found throws a notFoundException
     */
    @CrossOrigin
    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(Long.parseLong(id));
    }

}
