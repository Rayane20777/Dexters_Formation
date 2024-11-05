package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassesService {
    Classes create(Classes classes);
    List<Classes> getAll();
    Optional<Classes> getById(UUID id);
    Classes update(UUID id, Classes classes);
    void delete(UUID id);
    
    List<Classes> findByName(String name);
    List<Classes> findByRoomNumber(int roomNumber);
    List<Classes> findByProgramId(UUID programId);
    List<Classes> findClassesWithAvailableSpots(int maxCapacity);
    List<Classes> findClassesByInstructorSpeciality(String speciality);
    
    // Pagination
    Page<Classes> findByNameContaining(String namePart, Pageable pageable);
    Page<Classes> findByInstructorIsNull(Pageable pageable);
}
