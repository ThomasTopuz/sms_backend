package ch.thomastopuz.services;

import ch.thomastopuz.models.Teacher;
import ch.thomastopuz.repositories.SchoolClassRepository;
import ch.thomastopuz.models.SchoolClass;
import ch.thomastopuz.models.Student;
import ch.thomastopuz.repositories.StudentRepository;
import ch.thomastopuz.repositories.TeacherRepository;
import ch.thomastopuz.utils.AsyncOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // makes this class injectable
public class SchoolClassService {

    SchoolClassRepository schoolClassRepository;
    StudentRepository studentRepository;
    TeacherRepository teacherRepository;
    AsyncOperation asyncOperation;

    @Autowired // spring framework will inject the studentrespository from the bean
    public SchoolClassService(SchoolClassRepository classRepository, StudentRepository studentRepository,
                              TeacherRepository teacherRepository, AsyncOperation asyncOperation) {
        this.schoolClassRepository = classRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.asyncOperation = asyncOperation;
    }

    public List<SchoolClass> getClasses() {
        return schoolClassRepository.findAll();
    }

    public SchoolClass getById(Long id) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(id);
        return schoolClass.orElse(null);
    }

    public SchoolClass createSchoolClass(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    @Transactional
    public SchoolClass deleteSchoolClass(Long id) {
        Optional<SchoolClass> deletedSchoolClass = schoolClassRepository.findById(id);
        if (deletedSchoolClass.isPresent()) {
            removeAssociations(id);
            asyncOperation.await(() -> schoolClassRepository.deleteById(id), 1000);
            return deletedSchoolClass.get();
        } else {
            return null;
        }
    }

    @Transactional
    public void removeAssociations(Long schoolClassId) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(schoolClassId);
        if (schoolClass.isEmpty()) return;
        schoolClass.get().setStudents(new ArrayList<>()); // empty array list
        schoolClass.get().setTeacher(null);
    }

    @Transactional
    public SchoolClass addStudent(Long schoolClassId, Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(schoolClassId);
        if (student.isEmpty() || schoolClass.isEmpty()) {
            throw new IllegalStateException();
        }
        schoolClass.get().addStudent(student.get());
        return schoolClass.get();
    }

    @Transactional
    public Student removeStudent(Long studentId, Long schoolClassId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(schoolClassId);
        if (student.isEmpty() || schoolClass.isEmpty()) return null;

        schoolClass.get().removeStudent(student.get());
        return student.get();
    }

    @Transactional
    public SchoolClass assignTeacher(Long schoolClassId, Long teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(schoolClassId);
        if (teacher.isEmpty() || schoolClass.isEmpty()) {
            throw new IllegalStateException();
        }
        schoolClass.get().setTeacher(teacher.get());
        return schoolClass.get();
    }

}
