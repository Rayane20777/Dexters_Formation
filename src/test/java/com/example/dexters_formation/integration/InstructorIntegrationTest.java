package com.example.dexters_formation.integration;

import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.repository.InstructorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InstructorIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InstructorRepository instructorRepository;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/instructors";
    }

    @AfterEach
    void cleanup() {
        instructorRepository.deleteAll();
    }

    private Instructor createTestInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setEmail("ji.doe@example.com");
        instructor.setSpeciality("Java Programming");
        return instructor;
    }

    @Test
    void testCreateInstructor() {
        Instructor instructor = createTestInstructor();
        ResponseEntity<Instructor> response = restTemplate.postForEntity(
            getBaseUrl(), instructor, Instructor.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(instructor.getEmail(), response.getBody().getEmail());
    }

    @Test
    void testGetAllInstructors() {
        Instructor savedInstructor = instructorRepository.save(createTestInstructor());
        
        ResponseEntity<Instructor[]> response = restTemplate.getForEntity(
            getBaseUrl(), Instructor[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        assertEquals(savedInstructor.getEmail(), response.getBody()[0].getEmail());
    }

    @Test
    void testGetInstructorById() {
        Instructor savedInstructor = instructorRepository.save(createTestInstructor());

        ResponseEntity<Instructor> response = restTemplate.getForEntity(
            getBaseUrl() + "/" + savedInstructor.getId(), Instructor.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedInstructor.getId(), response.getBody().getId());
        assertEquals(savedInstructor.getEmail(), response.getBody().getEmail());
    }

    @Test
    void testUpdateInstructor() {
        Instructor savedInstructor = instructorRepository.save(createTestInstructor());
        savedInstructor.setFirstName("Jane");
        savedInstructor.setEmail("jane.doe@example.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Instructor> requestEntity = new HttpEntity<>(savedInstructor, headers);

        ResponseEntity<Instructor> response = restTemplate.exchange(
            getBaseUrl() + "/" + savedInstructor.getId(),
            HttpMethod.PUT,
            requestEntity,
            Instructor.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane", response.getBody().getFirstName());
        assertEquals("jane.doe@example.com", response.getBody().getEmail());
    }

    @Test
    void testDeleteInstructor() {
        Instructor savedInstructor = instructorRepository.save(createTestInstructor());
        UUID instructorId = savedInstructor.getId();

        restTemplate.delete(getBaseUrl() + "/" + instructorId);

        ResponseEntity<String> response = restTemplate.getForEntity(
            getBaseUrl() + "/" + instructorId, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(instructorRepository.existsById(instructorId));
    }


} 