package com.example.dexters_formation.integration;

import com.example.dexters_formation.entity.Learner;
import com.example.dexters_formation.repository.LearnerRepository;
import com.example.dexters_formation.repository.ClassesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LearnerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private ClassesRepository classesRepository;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/learners";
    }

    @AfterEach
    void cleanup() {
        classesRepository.deleteAll();
        learnerRepository.deleteAll();
    }

    private Learner createTestLearner() {
        Learner learner = new Learner();
        learner.setFirstName("Jane");
        learner.setLastName("Smith");
        learner.setEmail("jane.smith@example.com");
        learner.setLevel("INTERMEDIATE");
        return learner;
    }

    @Test
    void testCreateLearner() {
        Learner learner = createTestLearner();
        ResponseEntity<Learner> response = restTemplate.postForEntity(
            getBaseUrl(), learner, Learner.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(learner.getEmail(), response.getBody().getEmail());
    }

    @Test
    void testGetAllLearners() {
        Learner savedLearner = learnerRepository.save(createTestLearner());
        
        ResponseEntity<Learner[]> response = restTemplate.getForEntity(
            getBaseUrl(), Learner[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void testGetLearnerById() {
        Learner savedLearner = learnerRepository.save(createTestLearner());
        
        ResponseEntity<Learner> response = restTemplate.getForEntity(
            getBaseUrl() + "/" + savedLearner.getId(), Learner.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedLearner.getId(), response.getBody().getId());
    }

    @Test
    void testUpdateLearner() {
        Learner savedLearner = learnerRepository.save(createTestLearner());
        savedLearner.setFirstName("Janet");
        
        restTemplate.put(getBaseUrl() + "/" + savedLearner.getId(), savedLearner);
        
        Learner updatedLearner = learnerRepository.findById(savedLearner.getId()).orElse(null);
        assertNotNull(updatedLearner);
        assertEquals("Janet", updatedLearner.getFirstName());
    }

    @Test
    void testDeleteLearner() {
        Learner savedLearner = learnerRepository.save(createTestLearner());
        
        restTemplate.delete(getBaseUrl() + "/" + savedLearner.getId());
        
        Optional<Learner> deletedLearner = learnerRepository.findById(savedLearner.getId());
        assertFalse(deletedLearner.isPresent());
    }

    @Test
    void testFindByEmailContaining() {
        Learner savedLearner = learnerRepository.save(createTestLearner());
        
        ResponseEntity<Learner[]> response = restTemplate.getForEntity(
            getBaseUrl() + "/by-email?emailDomain=example.com", Learner[].class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }


} 