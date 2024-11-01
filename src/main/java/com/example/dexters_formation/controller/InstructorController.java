package com.example.dexters_formation.controller;


import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.service.interfaces.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping
    public Instructor create(@Valid @RequestBody Instructor instructor) {
        return instructorService.create(instructor);
    }

    @GetMapping
    public List<Instructor> getAll() {
        return instructorService.getAll();
    }

    @GetMapping("/{id}")
    public Instructor getById(@PathVariable UUID id) {
        return instructorService.getById(id);
    }

    @PutMapping("/{id}")
    public Instructor update(@PathVariable UUID id, @Valid @RequestBody Instructor instructor) {
        return instructorService.update(id, instructor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        instructorService.delete(id);
    }
}
