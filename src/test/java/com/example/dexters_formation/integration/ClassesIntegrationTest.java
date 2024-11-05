package com.example.dexters_formation.integration;

import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.repository.ClassesRepository;
import com.example.dexters_formation.repository.InstructorRepository;
import com.example.dexters_formation.repository.ProgramRepository;
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
class ClassesIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private ProgramRepository programRepository;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/classes";
    }

    @AfterEach
    void cleanup() {
        classesRepository.deleteAll();
        instructorRepository.deleteAll();
        programRepository.deleteAll();
    }

    private Classes createTestClass() {
        // Create and save instructor
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setEmail("ds.doe@example.com");
        instructor.setSpeciality("Java");
        instructor = instructorRepository.save(instructor);

        // Create and save program (using createTestProgram from ProgramIntegrationTest)
        Program program = new Program();
        program.setTitle("Test Program");
        program.setLevel(1);
        program.setPrerequisites("None");
        program.setMinCapacity(5);
        program.setMaxCapacity(10);
        program = programRepository.save(program);

        // Create class
        Classes classes = new Classes();
        classes.setName("Java Fundamentals");
        classes.setRoomNumber(47);
        classes.setInstructor(instructor);
        classes.setProgram(program);
        return classes;
    }

    @Test
    void testCreateClass() {
        Classes classes = createTestClass();
        ResponseEntity<Classes> response = restTemplate.postForEntity(
            getBaseUrl(), classes, Classes.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(classes.getName(), response.getBody().getName());
        assertNotNull(response.getBody().getInstructor());
        assertNotNull(response.getBody().getProgram());
    }

    @Test
    void testGetAllClasses() {
        Classes savedClass = classesRepository.save(createTestClass());
        
        ResponseEntity<Classes[]> response = restTemplate.getForEntity(
            getBaseUrl(), Classes[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        assertEquals(savedClass.getName(), response.getBody()[0].getName());
    }

    @Test
    void testGetClassById() {
        Classes savedClass = classesRepository.save(createTestClass());

        ResponseEntity<Classes> response = restTemplate.getForEntity(
            getBaseUrl() + "/" + savedClass.getId(), Classes.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedClass.getId(), response.getBody().getId());
        assertEquals(savedClass.getName(), response.getBody().getName());
    }

    @Test
    void testUpdateClass() {
        Classes savedClass = classesRepository.save(createTestClass());
        savedClass.setName("Advanced Java");
        savedClass.setRoomNumber(75);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Classes> requestEntity = new HttpEntity<>(savedClass, headers);

        ResponseEntity<Classes> response = restTemplate.exchange(
            getBaseUrl() + "/" + savedClass.getId(),
            HttpMethod.PUT,
            requestEntity,
            Classes.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Advanced Java", response.getBody().getName());
        assertEquals("B202", response.getBody().getRoomNumber());
    }

    @Test
    void testDeleteClass() {
        Classes savedClass = classesRepository.save(createTestClass());
        UUID classId = savedClass.getId();

        restTemplate.delete(getBaseUrl() + "/" + classId);

        ResponseEntity<String> response = restTemplate.getForEntity(
            getBaseUrl() + "/" + classId, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(classesRepository.existsById(classId));
    }


} 