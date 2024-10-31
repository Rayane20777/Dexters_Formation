package com.example.dexters_formation.controller;


import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.service.interfaces.ClassesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
@Api(tags = "Class Management")
public class ClassController {
    private final ClassesService classesService;

    @ApiOperation(value = "Create a new class")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created class"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping
    public void create(@Valid @RequestBody Classes classes) {
        classesService.create(classes);
    }

    @ApiOperation(value = "Get all classes", notes = "Returns a list of all classes")
    @GetMapping
    public List<Classes> getAll() {
        return classesService.getAll();
    }

    @ApiOperation(value = "Get a class by ID", notes = "Returns a class based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved class"),
        @ApiResponse(code = 404, message = "Class not found")
    })
    @GetMapping("/{id}")
    public Classes getById(@PathVariable UUID id) {
        return classesService.getById(id);
    }

    @ApiOperation(value = "Update a class", notes = "Updates a class based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated class"),
        @ApiResponse(code = 404, message = "Class not found")
    })
    @PutMapping("/{id}")
    public Classes update(@PathVariable UUID id, @Valid @RequestBody Classes classes) {
        return classesService.update(id, classes);
    }

    @ApiOperation(value = "Delete a class", notes = "Deletes a class based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted class"),
        @ApiResponse(code = 404, message = "Class not found")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        classesService.delete(id);
    }
}
