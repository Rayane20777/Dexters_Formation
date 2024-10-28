package com.example.dexters_formation.controller;

import com.example.dexters_formation.entity.Learner;
import com.example.dexters_formation.service.interfaces.LearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/learners")

public class LearnerController {
    private final LearnerService learnerService;


    @PostMapping
    public Learner create(@Valid @RequestBody Learner learner) {
        return learnerService.create(learner);
    }

    @GetMapping
    public List<Learner> getAll() {
        return learnerService.getAll();
    }

    @GetMapping("/{id}")
    public Learner getById(@PathVariable UUID id) {
        return learnerService.getById(id);
    }
}
