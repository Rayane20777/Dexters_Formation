package com.example.dexters_formation.repository;

import com.example.dexters_formation.entity.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, UUID> {
    List<Classes> findByName(String name);
    List<Classes> findByRoomNumber(int roomNumber);
    List<Classes> findByProgramId(UUID programId);
    
    @Query("SELECT c FROM Classes c WHERE SIZE(c.learners) < :maxCapacity")
    List<Classes> findClassesWithAvailableSpots(@Param("maxCapacity") int maxCapacity);
    
    @Query("SELECT c FROM Classes c WHERE c.instructor.speciality = :speciality")
    List<Classes> findClassesByInstructorSpeciality(@Param("speciality") String speciality);
    
    // Pagination
    Page<Classes> findByNameContaining(String namePart, Pageable pageable);
    Page<Classes> findByInstructorIsNull(Pageable pageable);
}
