package ch.thomastopuz.repositories;

import ch.thomastopuz.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // custom queries with jpql dialect
}

