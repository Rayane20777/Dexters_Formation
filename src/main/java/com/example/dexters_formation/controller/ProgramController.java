package com.example.dexters_formation.controller;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.service.interfaces.ProgramService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/programs")
@Api(tags = "Program Management")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @ApiOperation(value = "Create a new program")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created program"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping
    public Program create(@Valid @RequestBody Program program) {
        return programService.create(program);
    }

    @ApiOperation(value = "Get all programs", notes = "Returns a list of all programs")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved programs"),
        @ApiResponse(code = 404, message = "Programs not found")
    })
    @GetMapping
    public List<Program> getAll() {
        return programService.getAll();
    }

    @ApiOperation(value = "Get a program by ID", notes = "Returns a program based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved program"),
        @ApiResponse(code = 404, message = "Program not found")
    })
    @GetMapping("/{id}")
    public Program getById(@PathVariable UUID id) {
        return programService.getById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));
    }

    @ApiOperation(value = "Update a program", notes = "Updates a program based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated program"),
        @ApiResponse(code = 404, message = "Program not found")
    })
    @PutMapping("/{id}")
    public Program update(@PathVariable UUID id, @Valid @RequestBody Program program) {
        return programService.update(id, program);
    }

    @ApiOperation(value = "Delete a program", notes = "Deletes a program based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted program"),
        @ApiResponse(code = 404, message = "Program not found")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        programService.delete(id);
    }
}
