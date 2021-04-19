package ch.thomastopuz.services;

import ch.thomastopuz.exception.ApiExceptionThrower;
import ch.thomastopuz.dto.Person.PersonCreateDto;
import ch.thomastopuz.dto.Person.PersonUpdateDto;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Teacher;
import ch.thomastopuz.repositories.TeacherRepository;
import ch.thomastopuz.utils.AsyncOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Teacher service layer, provides methods to manipulate the teacher entity
 */
@Service
public class TeacherService {
    /**
     * teacher JPA repository, is an interface to the database, provided by spring boot dependency injection engine
     */
    TeacherRepository teacherRepository;
    /**
     * utility class to throw api exception, provided by spring boot dependency injection engine
     */
    ApiExceptionThrower apiExceptionThrower;
    /**
     * utility class to execute functions after a given delay, is an interface to the database, provided by spring boot dependency injection engine
     */
    AsyncOperation asyncOperation;

    /**
     * teacher service constructor
     *
     * @param teacherRepository   teacher JPA repository
     * @param apiExceptionThrower utility class to throw api exception
     * @param asyncOperation      utility class to execute functions after a given delay
     */
    @Autowired
    public TeacherService(TeacherRepository teacherRepository, ApiExceptionThrower apiExceptionThrower,
                          AsyncOperation asyncOperation) {
        this.teacherRepository = teacherRepository;
        this.apiExceptionThrower = apiExceptionThrower;
        this.asyncOperation = asyncOperation;
    }

    /**
     * method to get all teacher from database
     *
     * @return teacher list
     */
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    /**
     * method to get a teacher given the id
     *
     * @param id the teacher id
     * @return the teacher retrieved, if not found throws a not found api exception
     */
    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) apiExceptionThrower.throwNotFoundException("teacher", id);
        return teacher.get();
    }

    /**
     * method to get teacher's schoolclasses
     *
     * @param id the teacher id
     * @return the teacher's schoolclasses, if not found throws a not found api exception
     */
    public List<SchoolClass> getSchoolClasses(Long id) {
        Teacher teacher = getTeacherById(id);
        return teacher.getSchoolClasses();
    }

    /**
     * method to create a new teacher
     *
     * @param teacher new teacher payload (createDTO)
     * @return the created teacher, if missing properties throws a bad request exception
     */
    public Teacher createTeacher(PersonCreateDto teacher) {
        if (!teacher.isValidForPOST())
            apiExceptionThrower.throwBadRequestException("Bad request, missing certain property!");
        return teacherRepository.save(new Teacher(teacher.getName(), teacher.getSurname(), teacher.getEmail(), teacher.getDob()));
    }

    /**
     * method to modify an existing teacher
     *
     * @param teacherId  the teacher id
     * @param newTeacher the new teacher payload (updateDTO)
     * @return the modifies teacher, if not found throws a not found api exception
     */
    @Transactional
    public Teacher setTeacher(Long teacherId, PersonUpdateDto newTeacher) {
        Teacher teacher = getTeacherById(teacherId);
        if (newTeacher.getName() != null)
            teacher.setName(newTeacher.getName());
        if (newTeacher.getSurname() != null)
            teacher.setSurname(newTeacher.getSurname());
        if (newTeacher.getEmail() != null)
            teacher.setEmail(newTeacher.getEmail());
        return teacher;
    }

    /**
     * method to delete an existing teacher
     *
     * @param id the teacher id
     * @return the deleted teacher, if not found throws a not found api exception
     */
    @Transactional
    public Teacher deleteTeacher(Long id) {
        Teacher deletedTeacher = getTeacherById(id);
        if (deletedTeacher.getSchoolClasses().size() > 0)
            apiExceptionThrower.throwBadRequestException("Bad request, can't delete teacher because has classes, change teacher in those first!");
        asyncOperation.await(() -> teacherRepository.deleteById(id), 1000);
        return deletedTeacher;
    }
}
