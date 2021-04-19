package ch.thomastopuz.controllers;

import ch.thomastopuz.dto.Person.PersonCreateDto;
import ch.thomastopuz.dto.Person.PersonUpdateDto;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Teacher;
import ch.thomastopuz.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API controller responsible for the teacher entity, defines API endpoints and calls service methods
 */

@RequestMapping("api/v1/teacher")
@RestController
public class TeacherController {

    /**
     * teacher service provided by spring boot's dependency injection engine
     */
    TeacherService teacherService;

    /**
     * controller constructor that is called by spring boot
     *
     * @param teacherService the teacherService
     */
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * API endpoint to get all teachers
     *
     * @return the teacher list
     */
    @CrossOrigin
    @GetMapping()
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    /**
     * API endpoint to GET a teacher by its id
     *
     * @param id the teacher id
     * @return the teacher found in the database, if not found throws a notFoundException
     */
    @CrossOrigin
    @GetMapping("{id}")
    public Teacher getTeacherById(@PathVariable String id) {
        return teacherService.getTeacherById(Long.parseLong(id));
    }

    /**
     * API endpoint to get the teacher's schoolclasses
     *
     * @param id the teacher id
     * @return the teacher's schoolclasses, if not found throws a notFoundException and returns its payload
     */
    @CrossOrigin
    @GetMapping("{id}/schoolclasses")
    public List<SchoolClass> getSchoolClasses(@PathVariable String id) {
        return teacherService.getSchoolClasses(Long.parseLong(id));
    }

    /**
     * API endpoint to POST a new teacher
     *
     * @param teacher the new teacher payload
     * @return the created teacher, throw a badRequest exception if missing certain properties
     */
    @CrossOrigin
    @PostMapping
    public Teacher createTeacher(@RequestBody PersonCreateDto teacher) {
        return teacherService.createTeacher(teacher);
    }

    /**
     * API endpoint to PUT (modify) an existing teacher
     *
     * @param id      the teacher's id
     * @param teacher the new teacher payload
     * @return the modified teacher, if not found throws a notFoundException
     */
    @CrossOrigin
    @PutMapping("{id}")
    public Teacher setTeacher(@PathVariable String id, @RequestBody PersonUpdateDto teacher) {
        return teacherService.setTeacher(Long.parseLong(id), teacher);
    }

    /**
     * API endpoint to DELETE a teacher
     *
     * @param id the teacher id
     * @return the deleted teacher, if not found throws a notFoundException
     */
    @CrossOrigin
    @DeleteMapping("{id}")
    public Teacher deleteTeacher(@PathVariable String id) {
        return teacherService.deleteTeacher(Long.parseLong(id));
    }

}
