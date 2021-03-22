package ch.thomastopuz.schoolmanagementsystem.controllers;

import ch.thomastopuz.schoolmanagementsystem.models.SchoolClass;
import ch.thomastopuz.schoolmanagementsystem.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // return JSON data, for testing
public class ClassController {

    ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("api/v1/classes")
    public List<SchoolClass> getClasses() {
        return classService.getClasses();
    }

    @GetMapping("api/v1/classes/{id}")
    public SchoolClass getClassById(@PathVariable String id) {
        return classService.getById(Long.parseLong(id));
    }

    @PostMapping("api/v1/classes")
    public SchoolClass postClass(@RequestBody SchoolClass schoolClass) {
        return classService.createSchoolClass(schoolClass);
    }

    @DeleteMapping("api/v1/classes/{id}")
    public SchoolClass deleteClass(@PathVariable String id) {
        return classService.deleteSchoolClass(Long.parseLong(id));
    }
}
