package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.repository.ProgramRepository;
import com.example.dexters_formation.service.interfaces.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


}
