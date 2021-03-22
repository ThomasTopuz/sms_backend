package ch.thomastopuz.schoolmanagementsystem.services;

import ch.thomastopuz.schoolmanagementsystem.models.SchoolClass;
import ch.thomastopuz.schoolmanagementsystem.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // makes this class injectable
public class ClassService {

    ClassRepository classRepository;

    @Autowired // spring framework will inject the studentrespository from the bean
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<SchoolClass> getClasses () {
        return classRepository.findAll();
    }

    public SchoolClass getById(Long id) {
        Optional<SchoolClass> schoolClass = classRepository.findById(id);
        return schoolClass.orElse(null);
    }

    public SchoolClass createSchoolClass(SchoolClass schoolClass) {
        return classRepository.save(schoolClass);
    }

    public SchoolClass deleteSchoolClass(Long id) {
        Optional<SchoolClass> deletedSchoolClass = classRepository.findById(id);
        if(deletedSchoolClass.isPresent()) {
            classRepository.deleteById(id);
            return deletedSchoolClass.get();
        } else {
            return null;
        }

    }

}
