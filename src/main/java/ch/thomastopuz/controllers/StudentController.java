package ch.thomastopuz.controllers;

import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    StudentService studentService; // dependency injection

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable String id) {
        return studentService.getStudentById(Long.parseLong(id));
    }

    @GetMapping("{id}/schoolclasses")
    public List<SchoolClass> getSchoolClasses(@PathVariable String id) {
        return studentService.getSchoolClasses(Long.parseLong(id));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("{id}")
    public Student setStudent(@PathVariable String id, @RequestBody Student student) {
        return studentService.setStudent(Long.parseLong(id), student);
    }

    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(Long.parseLong(id));
    }

}
