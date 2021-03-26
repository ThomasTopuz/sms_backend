package ch.thomastopuz.controllers;

import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Teacher;
import ch.thomastopuz.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/teacher")
@RestController
public class TeacherController {

    TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("{id}")
    public Teacher getTeacherById(@PathVariable String id) {
        return teacherService.getTeacherById(Long.parseLong(id));
    }

    @GetMapping("{id}/schoolclasses")
    public List<SchoolClass> getSchoolClasses(@PathVariable String id) {
        return teacherService.getSchoolClasses(Long.parseLong(id));
    }

    @PostMapping("")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("{id}")
    public Teacher setStudent(@PathVariable String id, @RequestBody Teacher teacher) {
        return teacherService.setTeacher(Long.parseLong(id), teacher);
    }

    @DeleteMapping("{id}")
    public Teacher deleteTeacher(@PathVariable String id) {
        return teacherService.deleteTeacher(Long.parseLong(id));
    }

}