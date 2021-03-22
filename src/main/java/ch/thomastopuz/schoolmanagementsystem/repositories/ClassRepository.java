package ch.thomastopuz.schoolmanagementsystem.repositories;

import ch.thomastopuz.schoolmanagementsystem.models.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<SchoolClass, Long> {
    // custom queries with jpql dialect
}
