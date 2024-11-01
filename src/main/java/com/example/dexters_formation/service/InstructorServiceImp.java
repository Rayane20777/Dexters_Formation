package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.service.interfaces.InstructorService;
import com.example.dexters_formation.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorServiceImp implements InstructorService {
    private final InstructorRepository instructorRepository;

    @Override
    public Instructor create(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getById(UUID id) {
        return instructorRepository.findById(id).orElse(null);
    }

    @Override
    public Instructor update(UUID id, Instructor instructor) {
        if (instructorRepository.existsById(id)) {
            instructor.setId(id);
            return instructorRepository.save(instructor);
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
        }
    }
}
