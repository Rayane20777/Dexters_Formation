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

import java.util.Date;
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
        assertEquals(ProgramStatus.PLANNED, response.getBody().getStatus());
    }

    @Test
    void testGetAllPrograms() {
        Program savedProgram = programRepository.save(createTestProgram());
        
        ResponseEntity<Program[]> response = restTemplate.getForEntity(
            getBaseUrl(), Program[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        assertEquals(savedProgram.getTitle(), response.getBody()[0].getTitle());
    }

    @Test
    void testGetProgramById() {
        Program savedProgram = programRepository.save(createTestProgram());

        ResponseEntity<Program> response = restTemplate.getForEntity(
            getBaseUrl() + "/" + savedProgram.getId(), Program.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedProgram.getId(), response.getBody().getId());
        assertEquals(savedProgram.getStatus(), response.getBody().getStatus());
    }

    @Test
    void testUpdateProgram() {
        Program savedProgram = programRepository.save(createTestProgram());
        savedProgram.setTitle("Updated Title");
        savedProgram.setStatus(ProgramStatus.IN_PROGRESS);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Program> requestEntity = new HttpEntity<>(savedProgram, headers);

        ResponseEntity<Program> response = restTemplate.exchange(
            getBaseUrl() + "/" + savedProgram.getId(),
            HttpMethod.PUT,
            requestEntity,
            Program.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Title", response.getBody().getTitle());
        assertEquals(ProgramStatus.IN_PROGRESS, response.getBody().getStatus());
    }

    @Test
    void testDeleteProgram() {
        Program savedProgram = programRepository.save(createTestProgram());
        UUID programId = savedProgram.getId();

        restTemplate.delete(getBaseUrl() + "/" + programId);

        // Verify the program is deleted
        ResponseEntity<String> response = restTemplate.getForEntity(
            getBaseUrl() + "/" + programId, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(programRepository.existsById(programId));
    }

}
