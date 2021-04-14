package ch.thomastopuz.services;

import ch.thomastopuz.Exception.ApiExceptionThrower;
import ch.thomastopuz.dto.Person.PersonCreateDto;
import ch.thomastopuz.dto.Person.PersonUpdateDto;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.models.Teacher;
import ch.thomastopuz.repositories.SchoolClassRepository;
import ch.thomastopuz.repositories.TeacherRepository;
import ch.thomastopuz.utils.AsyncOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    TeacherRepository teacherRepository;
    ApiExceptionThrower apiExceptionThrower;
    AsyncOperation asyncOperation;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, ApiExceptionThrower apiExceptionThrower, AsyncOperation asyncOperation) {
        this.teacherRepository = teacherRepository;
        this.apiExceptionThrower = apiExceptionThrower;
        this.asyncOperation = asyncOperation;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) apiExceptionThrower.throwNotFoundException("teacher", id);
        return teacher.get();
    }

    public List<SchoolClass> getSchoolClasses(Long id) {
        Teacher teacher = getTeacherById(id);
        return teacher.getSchoolClasses();
    }

    public Teacher createTeacher(PersonCreateDto teacher) {
        if (!teacher.isValidForPOST()) apiExceptionThrower.throwBadRequestException("Bad request, missing certain property!");
        return teacherRepository.save(new Teacher(teacher.getName(), teacher.getSurname(), teacher.getEmail(), teacher.getDob()));
    }

    @Transactional
    public Teacher deleteTeacher(Long id) {
        Teacher deletedTeacher = getTeacherById(id);
        // clear associations, set used teachers to null value
        removeAssociations(id);
        asyncOperation.await(() -> teacherRepository.deleteById(id), 1000);
        return deletedTeacher;
    }

    private void removeAssociations(Long teacherId) {
        Teacher teacher = getTeacherById(teacherId);
        for (SchoolClass schoolClass : teacher.getSchoolClasses()) {
            schoolClass.setTeacher(null);
        }
    }

    @Transactional
    public Teacher setTeacher(Long teacherId, PersonUpdateDto newTeacher) {
        Teacher teacher = getTeacherById(teacherId);
        if(newTeacher.getName()!=null)
            teacher.setName(newTeacher.getName());
        if(newTeacher.getSurname()!=null)
            teacher.setSurname(newTeacher.getSurname());
        if(newTeacher.getEmail()!=null)
            teacher.setEmail(newTeacher.getEmail());
        return teacher;
    }

}
