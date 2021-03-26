package ch.thomastopuz.services;

import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.models.Teacher;
import ch.thomastopuz.repositories.SchoolClassRepository;
import ch.thomastopuz.repositories.TeacherRepository;
import ch.thomastopuz.utils.AsyncOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    SchoolClassRepository classRepository;
    TeacherRepository teacherRepository;
    AsyncOperation asyncOperation;

    @Autowired
    public TeacherService(SchoolClassRepository classRepository, TeacherRepository teacherRepository, AsyncOperation asyncOperation) {
        this.classRepository = classRepository;
        this.teacherRepository = teacherRepository;
        this.asyncOperation = asyncOperation;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.orElse(null);
    }

    public List<SchoolClass> getSchoolClasses(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) return null;
        return teacher.get().getSchoolClasses();
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Transactional
    public Teacher deleteTeacher(Long id) {
        Optional<Teacher> deletedTeacher = teacherRepository.findById(id);
        if (deletedTeacher.isPresent()) {
            // clear associations, set used teachers to null value
            removeAssociations(id);
            asyncOperation.await(() -> teacherRepository.deleteById(id), 1000);
            return deletedTeacher.get();
        } else {
            return null;
        }
    }

    private void removeAssociations(Long teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isEmpty()) return;
        for (SchoolClass schoolClass : teacher.get().getSchoolClasses()) {
            schoolClass.setTeacher(null);
        }
    }

    @Transactional
    public Teacher setTeacher(Long teacherId, Teacher newTeacher) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isEmpty()) return null;
        teacher.get().setName(newTeacher.getName());
        teacher.get().setSurname(newTeacher.getSurname());
        teacher.get().setEmail(newTeacher.getEmail());
        return teacher.get();
    }

}
