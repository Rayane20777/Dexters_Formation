package com.example.dexters_formation.repository;

import com.example.dexters_formation.entity.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, UUID> {
    List<Instructor> findBySpeciality(String speciality);
    List<Instructor> findByLastNameStartingWith(String prefix);
    
    @Query("SELECT i FROM Instructor i WHERE i.classes IS NULL")
    List<Instructor> findAvailableInstructors();
    
    @Query("SELECT i FROM Instructor i WHERE i.speciality IN :specialities")
    List<Instructor> findBySpecialities(@Param("specialities") List<String> specialities);
    
    // Pagination 
    Page<Instructor> findBySpecialityContaining(String specialityPart, Pageable pageable);
    Page<Instructor> findByClassesIsNotNull(Pageable pageable);
}
