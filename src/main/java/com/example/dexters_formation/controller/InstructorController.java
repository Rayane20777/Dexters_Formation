package com.example.dexters_formation.controller;


import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.service.interfaces.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping
    public Instructor create(@Valid @RequestBody Instructor instructor) {
        return instructorService.create(instructor);
    }

}
