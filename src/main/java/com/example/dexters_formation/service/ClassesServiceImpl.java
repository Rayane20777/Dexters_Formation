package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.repository.ClassesRepository;
import com.example.dexters_formation.service.interfaces.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl  implements ClassesService {
    private final ClassesRepository classesRepository;

    @Override
    public Classes create(Classes classes) {
        return classesRepository.save(classes);
    }
}
