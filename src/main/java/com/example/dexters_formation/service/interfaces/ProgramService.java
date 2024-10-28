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
}
