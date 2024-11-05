package com.example.dexters_formation.integration;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.entity.enums.ProgramStatus;
import com.example.dexters_formation.repository.ProgramRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.data.domain.Page;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProgramIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProgramRepository programRepository;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/programs";
    }

    @AfterEach
    void cleanup() {
        programRepository.deleteAll();
    }

    private Program createTestProgram() {
        Program program = new Program();
        program.setTitle("Integration Test Program");
        program.setLevel(1);
        program.setPrerequisites("None");
        program.setMinCapacity(5);
        program.setMaxCapacity(10);
        program.setStartDate(new Date());
        program.setEndDate(new Date());
        program.setStatus(ProgramStatus.PLANNED);
        return program;
    }

    @Test
    void testCreateProgram() {
        Program program = createTestProgram();
        ResponseEntity<Program> response = restTemplate.postForEntity(
            getBaseUrl(), program, Program.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(program.getTitle(), response.getBody().getTitle());
    }

    @Test
    void testGetAllPrograms() {
        Program savedProgram = programRepository.save(createTestProgram());
        
        ResponseEntity<Program[]> response = restTemplate.getForEntity(
            getBaseUrl(), Program[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void testGetProgramById() {
        Program savedProgram = programRepository.save(createTestProgram());
        
        ResponseEntity<Program> response = restTemplate.getForEntity(
            getBaseUrl() + "/" + savedProgram.getId(), Program.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedProgram.getId(), response.getBody().getId());
    }

    @Test
    void testUpdateProgram() {
        Program savedProgram = programRepository.save(createTestProgram());
        savedProgram.setTitle("Updated Program");
        
        restTemplate.put(getBaseUrl() + "/" + savedProgram.getId(), savedProgram);
        
        Program updatedProgram = programRepository.findById(savedProgram.getId()).orElse(null);
        assertNotNull(updatedProgram);
        assertEquals("Updated Program", updatedProgram.getTitle());
    }

    @Test
    void testDeleteProgram() {
        Program savedProgram = programRepository.save(createTestProgram());
        
        restTemplate.delete(getBaseUrl() + "/" + savedProgram.getId());
        
        Optional<Program> deletedProgram = programRepository.findById(savedProgram.getId());
        assertFalse(deletedProgram.isPresent());
    }

    @Test
    void testFindByLevelAndStatus() {
        Program savedProgram = programRepository.save(createTestProgram());
        
        ResponseEntity<Program[]> response = restTemplate.getForEntity(
            getBaseUrl() + "/by-level-and-status?level=1&status=PLANNED", Program[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void testFindByStatus() {
        Program savedProgram = programRepository.save(createTestProgram());
        
        ResponseEntity<Page<Program>> response = restTemplate.exchange(
            getBaseUrl() + "/by-status?status=PLANNED&page=0&size=10",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Page<Program>>() {});
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getTotalElements() > 0);
    }
}
