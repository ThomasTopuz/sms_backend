package ch.thomastopuz.schoolmanagementsystem.repositories;

import ch.thomastopuz.schoolmanagementsystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // custom queries with jpql dialect

}
