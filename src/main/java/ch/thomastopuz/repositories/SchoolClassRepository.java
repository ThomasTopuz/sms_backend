package ch.thomastopuz.repositories;

import ch.thomastopuz.models.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    // custom queries with jpql dialect
}
