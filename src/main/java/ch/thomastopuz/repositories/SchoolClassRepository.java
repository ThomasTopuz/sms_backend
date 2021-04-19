package ch.thomastopuz.repositories;

import ch.thomastopuz.models.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * the injectable schoolclass repository, is the interface for the database,
 * extends JPA repository (spring boot JPA, built on top of Hibernate framework)
 */
@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    // custom queries with jpql dialect
}
