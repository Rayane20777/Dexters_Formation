package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Program;

import java.util.List;

public interface ProgramService {
    Program create(Program program);
    List<Program> getAll();
}
