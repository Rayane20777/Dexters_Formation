package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InstructorService {
    Instructor create(Instructor instructor);
    List<Instructor> getAll();
    Optional<Instructor> getById(UUID id);
    Instructor update(UUID id, Instructor instructor);
    void delete(UUID id);
    
    List<Instructor> findBySpeciality(String speciality);
    List<Instructor> findByLastNameStartingWith(String prefix);
    List<Instructor> findAvailableInstructors();
    List<Instructor> findBySpecialities(List<String> specialities);
    
    // Pagination
    Page<Instructor> findBySpecialityContaining(String specialityPart, Pageable pageable);
    Page<Instructor> findByClassesIsNotNull(Pageable pageable);
}
