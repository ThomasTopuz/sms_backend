package ch.thomastopuz.repositories;

import ch.thomastopuz.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * the injectable teacher repository, is the interface for the databse,
 * extends JPA repository (spring boot JPA, built on top of Hibernate framework)
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // custom queries with jpql dialect
}

