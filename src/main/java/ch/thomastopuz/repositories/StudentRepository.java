package ch.thomastopuz.repositories;

import ch.thomastopuz.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // custom queries with jpql dialect

}
