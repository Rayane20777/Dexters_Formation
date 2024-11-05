package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Program;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProgramService {
    Program create(Program program);
    List<Program> getAll();
    Optional<Program> getById(UUID id);
    Program update(UUID id, Program program);
    public void delete(UUID id);

    List<Program> findByTitle(String title);
    List<Program> findByTitleContaining(String titlePart);
    List<Program> findByLevelAndStatus(int level, ProgramStatus status);
    List<Program> findProgramsInDateRange(Date startDate, Date endDate);
    List<Program> findProgramsByCapacityRange(int capacity);

    // Pagination
    Page<Program> findByStatus(ProgramStatus status, Pageable pageable);
    Page<Program> findByLevelGreaterThanEqual(int minLevel, Pageable pageable);


}
