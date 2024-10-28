package com.example.dexters_formation.controller;

import com.example.dexters_formation.entity.Learner;
import com.example.dexters_formation.service.interfaces.LearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/learners")

public class LearnerController {
    private final LearnerService learnerService;
    

    @PostMapping
    public Learner create(@Valid @RequestBody Learner learner) {
        return learnerService.create(learner);
    }
}
