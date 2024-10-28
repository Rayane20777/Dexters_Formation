package com.example.dexters_formation.controller;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.service.interfaces.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<Program> create(@Valid @RequestBody Program program) {
        return ResponseEntity.ok(programService.create(program));
    }

    @GetMapping
    public ResponseEntity<List<Program>> getAll() {
        return ResponseEntity.ok(programService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Program> getById(@PathVariable UUID id) {
        return programService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Program> update(@PathVariable UUID id, @Valid @RequestBody Program program) {
        return ResponseEntity.ok(programService.update(id, program));
    }
}
