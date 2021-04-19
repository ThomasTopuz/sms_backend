package ch.thomastopuz.services;

import ch.thomastopuz.exception.ApiExceptionThrower;
import ch.thomastopuz.exception.NotFound.ApiNotFoundException;
import ch.thomastopuz.dto.SchoolClass.SchoolClassCreateUpdateDto;
import ch.thomastopuz.models.Teacher;
import ch.thomastopuz.repositories.SchoolClassRepository;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.utils.AsyncOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Schoolclass service layer, provides methods to manipulate the schoolclass entity
 */
@Service // makes this class injectable
public class SchoolClassService {
    /**
     * schoolclass JPA repository, is an interface to the database, provided by spring boot dependency injection engine
     */
    SchoolClassRepository schoolClassRepository;

    /**
     * student service, provides utility methods for the student entity, provided by spring boot dependency injection engine
     */
    StudentService studentService;

    /**
     * teacher service, provides utility methods for the teacher entity, provided by spring boot dependency injection engine
     */
    TeacherService teacherService;

    /**
     * utility class to throw api exception, provided by spring boot dependency injection engine
     */
    ApiExceptionThrower apiExceptionThrower;

    /**
     * utility class to execute functions after a given delay, is an interface to the database, provided by spring boot dependency injection engine
     */
    AsyncOperation asyncOperation;


    /**
     * schoolclass service constructror
     *
     * @param schoolClassRepository schoolclass JPA repository, is an interface to the database
     * @param studentService        student service, provides utility methods for the student
     * @param teacherService        teacher service, provides utility methods for the teacher entity,
     * @param asyncOperation        utility class to execute functions after a given delay
     * @param apiExceptionThrower   utility class to throw api exception
     */
    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository, StudentService studentService,
                              TeacherService teacherService, AsyncOperation asyncOperation, ApiExceptionThrower apiExceptionThrower) {
        this.schoolClassRepository = schoolClassRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.asyncOperation = asyncOperation;
        this.apiExceptionThrower = apiExceptionThrower;
    }

    /**
     * method to get all schoolclasses in the database
     *
     * @return schoolclasses list
     */
    public List<SchoolClass> getClasses() {
        return schoolClassRepository.findAll();
    }

    /**
     * method to get a schoolclass by its id
     *
     * @param id the schoolclass id
     * @return the schoolclass, if not found throws a not found api exception
     */
    public SchoolClass getById(Long id) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(id);
        if (schoolClass.isEmpty()) throw new ApiNotFoundException("Oops, schoolclass not found!", "schoolclass", id);
        return schoolClass.get();
    }

    /**
     * method to create a new schoolclass
     *
     * @param schoolClassCreateDto the schoolclass create payload
     * @return the create schoolclass, if missing properties throws a bad request exception, if teacher is not present throws not found exception
     */
    public SchoolClass createSchoolClass(SchoolClassCreateUpdateDto schoolClassCreateDto) {
        if (schoolClassCreateDto.getName() == null || schoolClassCreateDto.getTeacherId() == null)
            apiExceptionThrower.throwBadRequestException("Bad request, missing class name or teacher id!");

        Teacher teacher = teacherService.getTeacherById(schoolClassCreateDto.getTeacherId());
        return schoolClassRepository.save(new SchoolClass(schoolClassCreateDto.getName(), teacher));
    }

    /**
     * method to modify an existing schoolclass
     *
     * @param schoolClassId  the schoolclass id
     * @param schoolClassDto the schoolclass update payload
     * @return the updated schoolclass, if missing properties throws a bad request exception, if teacher  not found throws a bad request exception
     */
    @Transactional
    public SchoolClass setSchoolClass(long schoolClassId, SchoolClassCreateUpdateDto schoolClassDto) {
        if (schoolClassDto.getName() == null || schoolClassDto.getTeacherId() == null)
            apiExceptionThrower.throwBadRequestException("Bad request, missing class name or teacher id!");

        SchoolClass schoolClass = getById(schoolClassId);
        Teacher teacher = teacherService.getTeacherById(schoolClassDto.getTeacherId());
        schoolClass.setName(schoolClassDto.getName());
        schoolClass.setTeacher(teacher);
        return schoolClass;
    }

    /**
     * method to delete a schoolclass
     *
     * @param id the schoolclass id
     * @return the deleted schoolclass, if not found throws a not found api exception
     */
    @Transactional
    public SchoolClass deleteSchoolClass(Long id) {
        SchoolClass deletedSchoolClass = getById(id);
        removeAssociations(id);
        asyncOperation.await(() -> schoolClassRepository.deleteById(id), 1000);
        return deletedSchoolClass;
    }

    /**
     * utility method used when deleting schoolclass, since is a bidirectional mapping we need to remove students and teacher dependency, to safely delete a schoolclass
     *
     * @param schoolClassId the schoolclass id, if not found throws a not found api exception
     */
    @Transactional
    public void removeAssociations(Long schoolClassId) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(schoolClassId);
        if (schoolClass.isEmpty()) apiExceptionThrower.throwNotFoundException("schoolclass", schoolClassId);
        schoolClass.get().setStudents(new ArrayList<>()); // empty array list
        schoolClass.get().setTeacher(null);
    }

    /**
     * method to add a student to an existing schoolclass
     *
     * @param schoolClassId the schoolclass id
     * @param studentId     the student id
     * @return the modified schoolclass, if student or schoolclass not found throws a not found api exception
     */
    @Transactional
    public SchoolClass addStudent(Long schoolClassId, Long studentId) {
        Student student = studentService.getStudentById(studentId);
        SchoolClass schoolClass = getById(schoolClassId);
        schoolClass.addStudent(student);
        return schoolClass;
    }

    /**
     * method to remove a student from a schoolclass
     *
     * @param studentId     the student id
     * @param schoolClassId the scholclass id
     * @return the modified schoolclass, if student or schoolclass not found throws a not found api exception
     */
    @Transactional
    public Student removeStudent(Long studentId, Long schoolClassId) {
        Student student = studentService.getStudentById(studentId);
        SchoolClass schoolClass = getById(schoolClassId);
        schoolClass.removeStudent(student);
        return student;
    }

    /**
     * method to assign a teacher to a schoolclass
     *
     * @param schoolClassId the schoolclass id
     * @param teacherId     the teacher id
     * @return the modified schoolclass, if teacher or schoolclass not found throws a not found api exception
     */
    @Transactional
    public SchoolClass assignTeacher(Long schoolClassId, Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        SchoolClass schoolClass = getById(schoolClassId);
        schoolClass.setTeacher(teacher);
        return schoolClass;
    }

}
