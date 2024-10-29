package com.example.dexters_formation.controller;


import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.service.interfaces.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
public class ClassController {
    private final ClassesService classesService;

    @PostMapping
    public void create(@Valid @RequestBody Classes classes) {
        classesService.create(classes);
    }

}
