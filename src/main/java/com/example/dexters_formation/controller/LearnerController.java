package com.example.dexters_formation.controller;

import com.example.dexters_formation.entity.Learner;
import com.example.dexters_formation.service.interfaces.LearnerService;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/learners")
@Api(tags = "Learner Management")
public class LearnerController {
    private final LearnerService learnerService;

    @ApiOperation(value = "Create a new learner")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created learner"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping
    public Learner create(@Valid @RequestBody Learner learner) {
        return learnerService.create(learner);
    }

    @ApiOperation(value = "Get all learners", notes = "Returns a list of all learners")
    @ApiResponse(code = 200, message = "Successfully retrieved learners")
    @GetMapping
    public List<Learner> getAll() {
        return learnerService.getAll();
    }

    @ApiOperation(value = "Get a learner by ID", notes = "Returns a learner based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved learner"),
        @ApiResponse(code = 404, message = "Learner not found")
    })
    @GetMapping("/{id}")
    public Learner getById(@PathVariable UUID id) {
        return learnerService.getById(id);
    }

    @ApiOperation(value = "Update a learner", notes = "Updates a learner based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated learner"),
        @ApiResponse(code = 404, message = "Learner not found")
    })
    @PutMapping("/{id}")
    public Learner update(@PathVariable UUID id, @Valid @RequestBody Learner learner) {
        return learnerService.update(id, learner);
    }

    @ApiOperation(value = "Delete a learner", notes = "Deletes a learner based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted learner"),
        @ApiResponse(code = 404, message = "Learner not found")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        learnerService.delete(id);
    }

    @ApiOperation(value = "Find learners by last name")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved learners")
    })
    @GetMapping("/by-lastname")
    public List<Learner> findByLastName(@RequestParam String lastName) {
        return learnerService.findByLastName(lastName);
    }

    @ApiOperation(value = "Find learners by first name and last name")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved learners")
    })
    @GetMapping("/by-full-name")
    public List<Learner> findByFirstNameAndLastName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return learnerService.findByFirstNameAndLastName(firstName, lastName);
    }

    @ApiOperation(value = "Find learners by email domain")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved learners")
    })
    @GetMapping("/by-email")
    public List<Learner> findByEmailContaining(@RequestParam String emailDomain) {
        return learnerService.findByEmailContaining(emailDomain);
    }

    @ApiOperation(value = "Find learners in a specific class")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved learners")
    })
    @GetMapping("/by-class")
    public List<Learner> findLearnersInClass(@RequestParam UUID classId) {
        return learnerService.findLearnersInClass(classId);
    }

    @ApiOperation(value = "Find learners by level and program")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved learners")
    })
    @GetMapping("/by-level-and-program")
    public List<Learner> findLearnersByLevelAndProgram(
            @RequestParam String level,
            @RequestParam UUID programId) {
        return learnerService.findLearnersByLevelAndProgram(level, programId);
    }

    @ApiOperation(value = "Find learners by level with pagination")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved learners")
    })
    @GetMapping("/by-level")
    public Page<Learner> findByLevel(
            @RequestParam String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return learnerService.findByLevel(level, PageRequest.of(page, size));
    }

    @ApiOperation(value = "Find learners without classes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved learners")
    })
    @GetMapping("/without-classes")
    public Page<Learner> findByClassesIsNull(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return learnerService.findByClassesIsNull(PageRequest.of(page, size));
    }
}