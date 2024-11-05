package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.repository.InstructorRepository;
import com.example.dexters_formation.service.interfaces.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
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
    public Optional<Instructor> getById(UUID id) {
        return instructorRepository.findById(id);
    }

    @Override
    public Instructor update(UUID id, Instructor instructor) {
        if (instructorRepository.existsById(id)) {
            instructor.setId(id);
            return instructorRepository.save(instructor);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found");
    }

    @Override
    public void delete(UUID id) {
        if (!instructorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found");
        }
        instructorRepository.deleteById(id);
    }

    @Override
    public List<Instructor> findBySpeciality(String speciality) {
        return instructorRepository.findBySpeciality(speciality);
    }

    @Override
    public List<Instructor> findByLastNameStartingWith(String prefix) {
        return instructorRepository.findByLastNameStartingWith(prefix);
    }

    @Override
    public List<Instructor> findAvailableInstructors() {
        return instructorRepository.findAvailableInstructors();
    }

    @Override
    public List<Instructor> findBySpecialities(List<String> specialities) {
        return instructorRepository.findBySpecialities(specialities);
    }

    @Override
    public Page<Instructor> findBySpecialityContaining(String specialityPart, Pageable pageable) {
        return instructorRepository.findBySpecialityContaining(specialityPart, pageable);
    }

    @Override
    public Page<Instructor> findByClassesIsNotNull(Pageable pageable) {
        return instructorRepository.findByClassesIsNotNull(pageable);
    }
}
