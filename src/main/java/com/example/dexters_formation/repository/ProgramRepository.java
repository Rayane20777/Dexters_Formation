package com.example.dexters_formation.repository;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.entity.enums.ProgramStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProgramRepository extends JpaRepository<Program, UUID> {
    List<Program> findByTitle(String title);
    List<Program> findByTitleContainingIgnoreCase(String titlePart);
    List<Program> findByLevelAndStatus(int level, ProgramStatus status);
    
    @Query("SELECT p FROM Program p WHERE p.startDate >= :startDate AND p.endDate <= :endDate")
    List<Program> findProgramsInDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    @Query("SELECT p FROM Program p WHERE p.minCapacity <= :capacity AND p.maxCapacity >= :capacity")
    List<Program> findProgramsByCapacityRange(@Param("capacity") int capacity);
    
    // Pagination
    Page<Program> findByStatus(ProgramStatus status, Pageable pageable);
    Page<Program> findByLevelGreaterThanEqual(int minLevel, Pageable pageable);
}
