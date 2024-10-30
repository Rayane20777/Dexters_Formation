package com.example.dexters_formation.unit;

import com.example.dexters_formation.entity.Learner;
import com.example.dexters_formation.repository.LearnerRepository;
import com.example.dexters_formation.service.LearnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LearnerServiceImplTest {

    @Mock
    private LearnerRepository learnerRepository;

    @InjectMocks
    private LearnerServiceImpl learnerService;

    private Learner learner;
    private UUID learnerId;

    @BeforeEach
    void setUp() {
        learnerId = UUID.randomUUID();
        learner = new Learner();
        learner.setId(learnerId);
        learner.setFirstName("Jane");
        learner.setLastName("Smith");
        learner.setEmail("jane.smith@example.com");
    }

    @Test
    void create() {
        when(learnerRepository.save(learner)).thenReturn(learner);

        Learner result = learnerService.create(learner);

        assertNotNull(result);
        assertEquals(learner.getFirstName(), result.getFirstName());
        assertEquals(learner.getEmail(), result.getEmail());
        verify(learnerRepository).save(learner);
    }

    @Test
    void getAll() {
        List<Learner> learners = Arrays.asList(learner);
        when(learnerRepository.findAll()).thenReturn(learners);

        List<Learner> result = learnerService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(learner.getFirstName(), result.get(0).getFirstName());
        verify(learnerRepository).findAll();
    }

    @Test
    void getById() {
        when(learnerRepository.findById(learnerId)).thenReturn(Optional.of(learner));

        Learner result = learnerService.getById(learnerId);

        assertNotNull(result);
        assertEquals(learner.getFirstName(), result.getFirstName());
        verify(learnerRepository).findById(learnerId);
    }

    @Test
    void update() {
        when(learnerRepository.existsById(learnerId)).thenReturn(true);
        when(learnerRepository.save(learner)).thenReturn(learner);

        Learner result = learnerService.update(learnerId, learner);

        assertNotNull(result);
        assertEquals(learner.getFirstName(), result.getFirstName());
        verify(learnerRepository).existsById(learnerId);
        verify(learnerRepository).save(learner);
    }

    @Test
    void delete() {
        when(learnerRepository.existsById(learnerId)).thenReturn(true);

        learnerService.delete(learnerId);

        verify(learnerRepository).existsById(learnerId);
        verify(learnerRepository).deleteById(learnerId);
    }
} 