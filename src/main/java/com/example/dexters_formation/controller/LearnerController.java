package com.example.dexters_formation.controller;

import com.example.dexters_formation.entity.Learner;
import com.example.dexters_formation.service.interfaces.LearnerService;
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
}
