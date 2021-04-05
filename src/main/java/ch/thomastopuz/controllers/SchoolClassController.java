package ch.thomastopuz.controllers;

import ch.thomastopuz.dto.SchoolClassCreateDto;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.services.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // return JSON data, for testing
@RequestMapping("api/v1/schoolclass")
public class SchoolClassController {

    SchoolClassService schoolClassService; // dependency injection

    @Autowired
    public SchoolClassController(SchoolClassService classService) {
        this.schoolClassService = classService;
    }

    @CrossOrigin
    @GetMapping
    public List<SchoolClass> getClasses() {
        System.out.println("foo");
        return schoolClassService.getClasses();
    }

    @CrossOrigin
    @GetMapping("{id}")
    public SchoolClass getClassById(@PathVariable String id) {
        return schoolClassService.getById(Long.parseLong(id));
    }

    @CrossOrigin
    @PostMapping
    public SchoolClass postClass(@RequestBody SchoolClassCreateDto schoolClass) {
        return schoolClassService.createSchoolClass(schoolClass);
    }

    @CrossOrigin
    @PutMapping("{schoolClassId}/addstudent/{studentId}")
    public SchoolClass addStudent(@PathVariable String schoolClassId, @PathVariable String studentId) {
        return schoolClassService.addStudent(Long.parseLong(schoolClassId), Long.parseLong(studentId));
    }

    @CrossOrigin
    @PutMapping("{schoolClassId}/removestudent/{studentId}")
    public Student removeStudent(@PathVariable String schoolClassId, @PathVariable String studentId) {
        return schoolClassService.removeStudent(Long.parseLong(studentId), Long.parseLong(schoolClassId));
    }

    @CrossOrigin
    @PutMapping("{schoolClassId}/assignteacher/{teacherId}")
    public SchoolClass assignTeacher(@PathVariable String schoolClassId, @PathVariable String teacherId) {
        return schoolClassService.assignTeacher(Long.parseLong(schoolClassId), Long.parseLong(teacherId));
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public SchoolClass deleteClass(@PathVariable String id) {
        return schoolClassService.deleteSchoolClass(Long.parseLong(id));
    }
}
