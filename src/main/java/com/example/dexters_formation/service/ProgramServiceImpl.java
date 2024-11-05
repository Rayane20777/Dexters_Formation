package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.entity.enums.ProgramStatus;
import com.example.dexters_formation.repository.ProgramRepository;
import com.example.dexters_formation.service.interfaces.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Override
    public Program create(Program program) {
        return programRepository.save(program);
    }

    @Override
    public List<Program> getAll() {
        return programRepository.findAll();
    }

    @Override
    public Optional<Program> getById(UUID id) {
        return programRepository.findById(id);
    }

    @Override
    public Program update(UUID id, Program program) {
        if (programRepository.existsById(id)) {
            program.setId(id);
            return programRepository.save(program);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found");
    }

    @Override
    public void delete(UUID id) {
        if (programRepository.existsById(id)) {
            programRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found");
        }
    }

    @Override
    public List<Program> findByTitle(String title) {
        return programRepository.findByTitle(title);
    }

    @Override
    public List<Program> findByTitleContaining(String titlePart) {
        return programRepository.findByTitleContainingIgnoreCase(titlePart);
    }

    @Override
    public List<Program> findByLevelAndStatus(int level, ProgramStatus status) {
        return programRepository.findByLevelAndStatus(level, status);
    }

    @Override
    public List<Program> findProgramsInDateRange(Date startDate, Date endDate) {
        return programRepository.findProgramsInDateRange(startDate, endDate);
    }

    @Override
    public List<Program> findProgramsByCapacityRange(int capacity) {
        return programRepository.findProgramsByCapacityRange(capacity);
    }

    @Override
    public Page<Program> findByStatus(ProgramStatus status, Pageable pageable) {
        return programRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Program> findByLevelGreaterThanEqual(int minLevel, Pageable pageable) {
        return programRepository.findByLevelGreaterThanEqual(minLevel, pageable);
    }
}
