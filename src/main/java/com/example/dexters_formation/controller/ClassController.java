package com.example.dexters_formation.controller;


import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.service.interfaces.ClassesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
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
    public Classes create(@Valid @RequestBody Classes classes) {
        return classesService.create(classes);
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
    public Optional<Classes> getById(@PathVariable UUID id) {
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

    @ApiOperation(value = "Find classes by name")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved classes")
    })
    @GetMapping("/by-name")
    public List<Classes> findByName(@RequestParam String name) {
        return classesService.findByName(name);
    }

    @ApiOperation(value = "Find classes by room number")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved classes")
    })
    @GetMapping("/by-room")
    public List<Classes> findByRoomNumber(@RequestParam int roomNumber) {
        return classesService.findByRoomNumber(roomNumber);
    }

    @ApiOperation(value = "Find classes by program ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved classes")
    })
    @GetMapping("/by-program")
    public List<Classes> findByProgramId(@RequestParam UUID programId) {
        return classesService.findByProgramId(programId);
    }

    @ApiOperation(value = "Find classes with available spots")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved classes")
    })
    @GetMapping("/available-spots")
    public List<Classes> findClassesWithAvailableSpots(@RequestParam int maxCapacity) {
        return classesService.findClassesWithAvailableSpots(maxCapacity);
    }

    @ApiOperation(value = "Find classes by instructor speciality")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved classes")
    })
    @GetMapping("/by-instructor-speciality")
    public List<Classes> findClassesByInstructorSpeciality(@RequestParam String speciality) {
        return classesService.findClassesByInstructorSpeciality(speciality);
    }

    @ApiOperation(value = "Search classes by name with pagination")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved classes")
    })
    @GetMapping("/search")
    public Page<Classes> findByNameContaining(
            @RequestParam String namePart,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return classesService.findByNameContaining(namePart, PageRequest.of(page, size));
    }

    @ApiOperation(value = "Find classes without instructor")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved classes")
    })
    @GetMapping("/without-instructor")
    public Page<Classes> findByInstructorIsNull(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return classesService.findByInstructorIsNull(PageRequest.of(page, size));
    }
}
