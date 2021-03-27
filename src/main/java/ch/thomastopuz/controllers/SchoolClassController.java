package ch.thomastopuz.controllers;

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

    @GetMapping
    public List<SchoolClass> getClasses() {
        return schoolClassService.getClasses();
    }

    @GetMapping("{id}")
    public SchoolClass getClassById(@PathVariable String id) {
        return schoolClassService.getById(Long.parseLong(id));
    }

    @PostMapping
    public SchoolClass postClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.createSchoolClass(schoolClass);
    }

    @PutMapping("{schoolClassId}/addstudent/{studentId}")
    public SchoolClass addStudent(@PathVariable String schoolClassId, @PathVariable String studentId) {
        return schoolClassService.addStudent(Long.parseLong(schoolClassId), Long.parseLong(studentId));
    }

    @PutMapping("{schoolClassId}/removestudent/{studentId}")
    public Student removeStudent(@PathVariable String schoolClassId, @PathVariable String studentId) {
        return schoolClassService.removeStudent(Long.parseLong(studentId), Long.parseLong(schoolClassId));
    }

    @PutMapping("{schoolClassId}/assignteacher/{teacherId}")
    public SchoolClass assignTeacher(@PathVariable String schoolClassId, @PathVariable String teacherId) {
        return schoolClassService.assignTeacher(Long.parseLong(schoolClassId), Long.parseLong(teacherId));
    }

    @DeleteMapping("{id}")
    public SchoolClass deleteClass(@PathVariable String id) {
        return schoolClassService.deleteSchoolClass(Long.parseLong(id));
    }
}
