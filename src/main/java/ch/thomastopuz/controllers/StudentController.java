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

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    StudentService studentService;
    SchoolClassService schoolClassService; // dependency injection

    @Autowired
    public StudentController(StudentService studentService, SchoolClassService schoolClassService) {
        this.studentService = studentService;
        this.schoolClassService = schoolClassService;
    }

    @CrossOrigin
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @CrossOrigin
    @GetMapping("{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(Long.parseLong(id));
    }

    @CrossOrigin
    @GetMapping("{id}/schoolclasses")
    public List<SchoolClass> getSchoolClasses(@PathVariable String id) {
        return studentService.getSchoolClasses(Long.parseLong(id));
    }

    @CrossOrigin
    @PostMapping
    public Student createStudent(@RequestBody PersonCreateDto student) {
        return studentService.createStudent(student);
    }

    @CrossOrigin
    @PutMapping("{id}")
    public Student setStudent(@PathVariable String id, @RequestBody PersonUpdateDto student) {
        return studentService.setStudent(Long.parseLong(id), student);
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(Long.parseLong(id));
    }

}
