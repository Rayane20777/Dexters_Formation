package com.example.dexters_formation.controller;


import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.service.interfaces.InstructorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Api(tags = "Instructor Management")
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @ApiOperation(value = "Create a new instructor")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created instructor"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping
    public Instructor create(@Valid @RequestBody Instructor instructor) {
        return instructorService.create(instructor);
    }

    @ApiOperation(value = "Get all instructors", notes = "Returns a list of all instructors")
    @ApiResponse(code = 200, message = "Successfully retrieved instructors")
    @GetMapping
    public List<Instructor> getAll() {
        return instructorService.getAll();
    }

    @ApiOperation(value = "Get an instructor by ID", notes = "Returns an instructor based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved instructor"),
        @ApiResponse(code = 404, message = "Instructor not found")
    })
    @GetMapping("/{id}")
    public Optional<Instructor> getById(@PathVariable UUID id) {
        return instructorService.getById(id);
    }

    @ApiOperation(value = "Update an instructor", notes = "Updates an instructor based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated instructor"),
        @ApiResponse(code = 404, message = "Instructor not found")
    })
    @PutMapping("/{id}")
    public Instructor update(@PathVariable UUID id, @Valid @RequestBody Instructor instructor) {
        return instructorService.update(id, instructor);
    }

    @ApiOperation(value = "Delete an instructor", notes = "Deletes an instructor based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted instructor"),
        @ApiResponse(code = 404, message = "Instructor not found")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        instructorService.delete(id);
    }
}
