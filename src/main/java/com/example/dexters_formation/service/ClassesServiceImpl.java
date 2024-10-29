package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.repository.ClassesRepository;
import com.example.dexters_formation.service.interfaces.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl  implements ClassesService {
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
    public Classes getById(UUID id) {
        return classesRepository.findById(id).orElse(null);
    }

    @Override
    public Classes update(UUID id, Classes classes) {
        if (classesRepository.existsById(id)) {
            classes.setId(id);
            return classesRepository.save(classes);
        }
        return null;
    }
}
