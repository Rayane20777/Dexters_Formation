package com.example.dexters_formation.controller;


import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.service.interfaces.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
public class ClassController {
    private final ClassesService classesService;

    @PostMapping
    public void create(@Valid @RequestBody Classes classes) {
        classesService.create(classes);
    }

    @GetMapping
    public List<Classes> getAll() {
        return classesService.getAll();
    }

    @GetMapping("/{id}")
    public Classes getById(@PathVariable UUID id) {
        return classesService.getById(id);
    }

    @PutMapping("/{id}")
    public Classes update(@PathVariable UUID id, @Valid @RequestBody Classes classes) {
        return classesService.update(id, classes);
    }
}
