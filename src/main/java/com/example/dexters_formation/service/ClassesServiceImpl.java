package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.repository.ClassesRepository;
import com.example.dexters_formation.service.interfaces.ClassesService;
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
public class ClassesServiceImpl implements ClassesService {
    private final ClassesRepository classesRepository;

    @Override
    public Classes create(Classes classes) {
        return classesRepository.save(classes);
    }

    @Override
    public List<Classes> getAll() {
        return classesRepository.findAll();
    }

    @Override
    public Optional<Classes> getById(UUID id) {
        return classesRepository.findById(id);
    }

    @Override
    public Classes update(UUID id, Classes classes) {
        if (classesRepository.existsById(id)) {
            classes.setId(id);
            return classesRepository.save(classes);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found");
    }

    @Override
    public void delete(UUID id) {
        if (!classesRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found");
        }
        classesRepository.deleteById(id);
    }

    @Override
    public List<Classes> findByName(String name) {
        return classesRepository.findByName(name);
    }

    @Override
    public List<Classes> findByRoomNumber(int roomNumber) {
        return classesRepository.findByRoomNumber(roomNumber);
    }

    @Override
    public List<Classes> findByProgramId(UUID programId) {
        return classesRepository.findByProgramId(programId);
    }

    @Override
    public List<Classes> findClassesWithAvailableSpots(int maxCapacity) {
        return classesRepository.findClassesWithAvailableSpots(maxCapacity);
    }

    @Override
    public List<Classes> findClassesByInstructorSpeciality(String speciality) {
        return classesRepository.findClassesByInstructorSpeciality(speciality);
    }

    @Override
    public Page<Classes> findByNameContaining(String namePart, Pageable pageable) {
        return classesRepository.findByNameContaining(namePart, pageable);
    }

    @Override
    public Page<Classes> findByInstructorIsNull(Pageable pageable) {
        return classesRepository.findByInstructorIsNull(pageable);
    }
}
