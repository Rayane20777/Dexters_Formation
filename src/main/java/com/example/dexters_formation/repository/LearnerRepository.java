package com.example.dexters_formation.repository;

import com.example.dexters_formation.entity.Learner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, UUID> {
    List<Learner> findByLastName(String lastName);
    List<Learner> findByFirstNameAndLastName(String firstName, String lastName);
    List<Learner> findByEmailContaining(String emailDomain);
    
    @Query("SELECT l FROM Learner l WHERE l.classes.id = :classId")
    List<Learner> findLearnersInClass(@Param("classId") UUID classId);
    
    @Query("SELECT l FROM Learner l WHERE l.level = :level AND l.classes.program.id = :programId")
    List<Learner> findLearnersByLevelAndProgram(@Param("level") String level, @Param("programId") UUID programId);
    
    // Pagination
    Page<Learner> findByLevel(String level, Pageable pageable);
    Page<Learner> findByClassesIsNull(Pageable pageable);
}
