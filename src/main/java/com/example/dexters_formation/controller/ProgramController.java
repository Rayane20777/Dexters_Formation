package com.example.dexters_formation.controller;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.service.interfaces.ProgramService;
import lombok.RequiredArgsConstructor;
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
    public Program create(@Valid @RequestBody Program program) {
        return programService.create(program);
    }

    @GetMapping
    public List<Program> getAll() {
        return programService.getAll();
    }

    @GetMapping("/{id}")
    public Program getById(@PathVariable UUID id) {
        return programService.getById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));
    }

    @PutMapping("/{id}")
    public Program update(@PathVariable UUID id, @Valid @RequestBody Program program) {
        return programService.update(id, program);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        programService.delete(id);
    }
}
