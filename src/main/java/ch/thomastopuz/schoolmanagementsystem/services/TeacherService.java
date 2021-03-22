package ch.thomastopuz.schoolmanagementsystem.services;

import ch.thomastopuz.schoolmanagementsystem.repositories.ClassRepository;
import ch.thomastopuz.schoolmanagementsystem.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    ClassRepository classRepository;
    TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(ClassRepository classRepository, TeacherRepository teacherRepository) {
        this.classRepository = classRepository;
        this.teacherRepository = teacherRepository;
    }


}
