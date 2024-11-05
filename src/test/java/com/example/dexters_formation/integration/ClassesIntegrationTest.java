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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.UUID;
import java.util.Optional;

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
        instructor.setEmail("john.doe@example.com");
        instructor.setSpeciality("Java");
        instructor = instructorRepository.save(instructor);

        // Create and save program
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
        classes.setRoomNumber(12);
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
    }

    @Test
    void testGetClassById() {
        Classes savedClass = classesRepository.save(createTestClass());
        
        ResponseEntity<Classes> response = restTemplate.getForEntity(
            getBaseUrl() + "/" + savedClass.getId(), Classes.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedClass.getId(), response.getBody().getId());
    }

    @Test
    void testUpdateClass() {
        Classes savedClass = classesRepository.save(createTestClass());
        savedClass.setName("Advanced Java");
        
        restTemplate.put(getBaseUrl() + "/" + savedClass.getId(), savedClass);
        
        Classes updatedClass = classesRepository.findById(savedClass.getId()).orElse(null);
        assertNotNull(updatedClass);
        assertEquals("Advanced Java", updatedClass.getName());
    }

    @Test
    void testDeleteClass() {
        Classes savedClass = classesRepository.save(createTestClass());
        
        restTemplate.delete(getBaseUrl() + "/" + savedClass.getId());
        
        Optional<Classes> deletedClass = classesRepository.findById(savedClass.getId());
        assertFalse(deletedClass.isPresent());
    }

    @Test
    void testFindByProgramId() {
        Classes savedClass = classesRepository.save(createTestClass());
        
        ResponseEntity<Classes[]> response = restTemplate.getForEntity(
            getBaseUrl() + "/by-program/" + savedClass.getProgram().getId(), Classes[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void testFindClassesWithAvailableSpots() {
        Classes savedClass = classesRepository.save(createTestClass());
        
        ResponseEntity<Classes[]> response = restTemplate.getForEntity(
            getBaseUrl() + "/available?minSpots=5", Classes[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testFindClassesByInstructorSpeciality() {
        Classes savedClass = classesRepository.save(createTestClass());
        
        ResponseEntity<Classes[]> response = restTemplate.getForEntity(
            getBaseUrl() + "/by-instructor-speciality?speciality=Java", Classes[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void testFindByNameContaining() {
        Classes savedClass = classesRepository.save(createTestClass());
        
        ResponseEntity<Page<Classes>> response = restTemplate.exchange(
            getBaseUrl() + "/by-name?name=Java&page=0&size=10",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Page<Classes>>() {});
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getTotalElements() > 0);
    }


} 