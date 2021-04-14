package ch.thomastopuz.services;

import ch.thomastopuz.Exception.ApiExceptionThrower;
import ch.thomastopuz.Exception.NotFound.ApiNotFoundException;
import ch.thomastopuz.dto.SchoolClass.SchoolClassCreateUpdateDto;
import ch.thomastopuz.models.Teacher;
import ch.thomastopuz.repositories.SchoolClassRepository;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.repositories.StudentRepository;
import ch.thomastopuz.repositories.TeacherRepository;
import ch.thomastopuz.utils.AsyncOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // makes this class injectable
public class SchoolClassService {

    SchoolClassRepository schoolClassRepository;
    StudentService studentService;
    TeacherService teacherService;
    ApiExceptionThrower apiExceptionThrower;
    AsyncOperation asyncOperation;

    @Autowired // spring framework will inject the studentrespository from the bean
    public SchoolClassService(SchoolClassRepository classRepository, StudentService studentService,
                              TeacherService teacherService, AsyncOperation asyncOperation, ApiExceptionThrower apiExceptionThrower) {
        this.schoolClassRepository = classRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.asyncOperation = asyncOperation;
        this.apiExceptionThrower = apiExceptionThrower;
    }

    public List<SchoolClass> getClasses() {
        return schoolClassRepository.findAll();
    }

    public SchoolClass getById(Long id) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(id);
        if (schoolClass.isEmpty()) throw new ApiNotFoundException("Oops, schoolclass not found!", "schoolclass", id);
        return schoolClass.get();
    }

    public SchoolClass createSchoolClass(SchoolClassCreateUpdateDto schoolClassCreateDto) {
        if (schoolClassCreateDto.getName() == null || schoolClassCreateDto.getTeacherId() == null)
            apiExceptionThrower.throwBadRequestException("Bad request, missing class name or teacher id!");

        Teacher teacher = teacherService.getTeacherById(schoolClassCreateDto.getTeacherId());
        return schoolClassRepository.save(new SchoolClass(schoolClassCreateDto.getName(), teacher));
    }

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

    @Transactional
    public SchoolClass deleteSchoolClass(Long id) {
        SchoolClass deletedSchoolClass = getById(id);
        removeAssociations(id);
        asyncOperation.await(() -> schoolClassRepository.deleteById(id), 1000);
        return deletedSchoolClass;
    }

    @Transactional
    public void removeAssociations(Long schoolClassId) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(schoolClassId);
        if (schoolClass.isEmpty()) apiExceptionThrower.throwNotFoundException("schoolclass", schoolClassId);
        schoolClass.get().setStudents(new ArrayList<>()); // empty array list
        schoolClass.get().setTeacher(null);
    }

    @Transactional
    public SchoolClass addStudent(Long schoolClassId, Long studentId) {
        Student student = studentService.getStudentById(studentId);
        SchoolClass schoolClass = getById(schoolClassId);
        schoolClass.addStudent(student);
        return schoolClass;
    }

    @Transactional
    public Student removeStudent(Long studentId, Long schoolClassId) {
        Student student = studentService.getStudentById(studentId);
        SchoolClass schoolClass = getById(schoolClassId);
        schoolClass.removeStudent(student);
        return student;
    }

    @Transactional
    public SchoolClass assignTeacher(Long schoolClassId, Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        SchoolClass schoolClass = getById(schoolClassId);
        schoolClass.setTeacher(teacher);
        return schoolClass;
    }

}
