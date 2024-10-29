package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.service.interfaces.InstructorService;
import com.example.dexters_formation.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstructorServiceImp implements InstructorService {
    private final InstructorRepository instructorRepository;

    @Override
    public Instructor create(Instructor instructor) {
        return instructorRepository.save(instructor);
    }
}
