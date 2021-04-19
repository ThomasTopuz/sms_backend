package ch.thomastopuz.repositories;

import ch.thomastopuz.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * the injectable student repository, is the interface for the databse,
 * extends JPA repository (spring boot JPA, built on top of Hibernate framework)
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // custom queries with jpql dialect

}
