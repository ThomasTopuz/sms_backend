package ch.thomastopuz.controllers;

import ch.thomastopuz.dto.SchoolClass.SchoolClassCreateUpdateDto;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.services.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API Controller responsible for the school class entity, defines API endpoints and calls service methods
 */
@RestController // return JSON data, for testing
@RequestMapping("api/v1/schoolclass")
public class SchoolClassController {

    /**
     * Inject the schoolClassService to call its methods
     */
    SchoolClassService schoolClassService; // dependency injection

    /**
     * Controller constructor
     *
     * @param schoolClassService schoolClass service that spring boot will provide this controller with
     */
    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    /**
     * API endpoints to GET the all schoolclasses
     *
     * @return the list of schoolclasses got from the database
     */
    @CrossOrigin
    @GetMapping
    public List<SchoolClass> getClasses() {
        return schoolClassService.getClasses();
    }

    /**
     * API endpoints to GET a single school class given a certain id as pathvariable
     *
     * @param id the school class id
     * @return schoolClass found, if not found throws a notFoundException and returns the exception payload
     */
    @CrossOrigin
    @GetMapping("{id}")
    public SchoolClass getClassById(@PathVariable String id) {
        return schoolClassService.getById(Long.parseLong(id));
    }

    /**
     * API endpoint to POST a new schoolClass
     *
     * @param schoolClass schoolClass dto, the object represents the resource that will be created
     * @return the saved schoolclass or throws a BAD REQUEST exception if missing certain properties
     */
    @CrossOrigin
    @PostMapping
    public SchoolClass postClass(@RequestBody SchoolClassCreateUpdateDto schoolClass) {
        return schoolClassService.createSchoolClass(schoolClass);
    }

    /**
     * API endpoint to PUT (modify) a schoolclass
     *
     * @param id             the schoolCLass id
     * @param schoolClassDto schoolClass dto that represents the modified payload
     * @return the modified schoolClass, if not found throws a notFoundException
     */
    @CrossOrigin
    @PutMapping("{id}")
    public SchoolClass setSchoolClass(@PathVariable String id, @RequestBody SchoolClassCreateUpdateDto schoolClassDto) {
        return schoolClassService.setSchoolClass(Long.parseLong(id), schoolClassDto);
    }

    /**
     * API endpoint to DELETE a schoolclass
     *
     * @param id the schoolclass id
     * @return the deleted schoolclass, if not found throws a notFoundException
     */
    @CrossOrigin
    @DeleteMapping("{id}")
    public SchoolClass deleteClass(@PathVariable String id) {
        return schoolClassService.deleteSchoolClass(Long.parseLong(id));
    }

    /**
     * API endpoint to add a student to a schoolclass
     *
     * @param schoolClassId the schoolclass id
     * @param studentId     the student's id
     * @return the modified schoolclass, if not found throws a notFoundException
     */
    @CrossOrigin
    @PutMapping("{schoolClassId}/addstudent/{studentId}")
    public SchoolClass addStudent(@PathVariable String schoolClassId, @PathVariable String studentId) {
        return schoolClassService.addStudent(Long.parseLong(schoolClassId), Long.parseLong(studentId));
    }

    /**
     * API endpoint to remove a student from a class
     *
     * @param schoolClassId the schoolclass id
     * @param studentId     the student's id
     * @return the modified schoolclass, if not found throws a notFoundException
     */
    @CrossOrigin
    @PutMapping("{schoolClassId}/removestudent/{studentId}")
    public Student removeStudent(@PathVariable String schoolClassId, @PathVariable String studentId) {
        return schoolClassService.removeStudent(Long.parseLong(studentId), Long.parseLong(schoolClassId));
    }

    /**
     * API endpoint to assign / modify teacher to a schoolclass
     *
     * @param schoolClassId schoolclass id
     * @param teacherId     the teacher id
     * @return the modified schoolclass, if not found throws a notFoundException
     */
    @CrossOrigin
    @PutMapping("{schoolClassId}/assignteacher/{teacherId}")
    public SchoolClass assignTeacher(@PathVariable String schoolClassId, @PathVariable String teacherId) {
        return schoolClassService.assignTeacher(Long.parseLong(schoolClassId), Long.parseLong(teacherId));
    }


}
