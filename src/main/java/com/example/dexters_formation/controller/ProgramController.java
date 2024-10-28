package com.example.dexters_formation.controller;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.service.interfaces.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<Program> create(@Valid @RequestBody Program program) {
        return ResponseEntity.ok(programService.create(program));
    }
}
