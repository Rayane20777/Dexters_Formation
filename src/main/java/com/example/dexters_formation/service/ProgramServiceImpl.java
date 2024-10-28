package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.repository.LearnerRepository;
import com.example.dexters_formation.repository.ProgramRepository;
import com.example.dexters_formation.service.interfaces.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Override
    public Program create(Program program) {
        return programRepository.save(program);
    }

}
