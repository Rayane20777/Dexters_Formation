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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LearnerServiceImplTest {

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
        learner.setLevel("INTERMEDIATE");
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

    @Test
    void findByEmailContaining() {
        String emailDomain = "@example.com";
        List<Learner> learners = Arrays.asList(learner);
        when(learnerRepository.findByEmailContaining(emailDomain)).thenReturn(learners);

        List<Learner> result = learnerService.findByEmailContaining(emailDomain);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getEmail().contains(emailDomain));
        verify(learnerRepository).findByEmailContaining(emailDomain);
    }

    @Test
    void findLearnersInClass() {
        UUID classId = UUID.randomUUID();
        List<Learner> learners = Arrays.asList(learner);
        when(learnerRepository.findLearnersInClass(classId)).thenReturn(learners);

        List<Learner> result = learnerService.findLearnersInClass(classId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(learnerRepository).findLearnersInClass(classId);
    }

    @Test
    void findLearnersByLevelAndProgram() {
        String level = "INTERMEDIATE";
        UUID programId = UUID.randomUUID();
        List<Learner> learners = Arrays.asList(learner);
        when(learnerRepository.findLearnersByLevelAndProgram(level, programId)).thenReturn(learners);

        List<Learner> result = learnerService.findLearnersByLevelAndProgram(level, programId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(level, result.get(0).getLevel());
        verify(learnerRepository).findLearnersByLevelAndProgram(level, programId);
    }

    @Test
    void findByLevel() {
        String level = "INTERMEDIATE";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Learner> page = new PageImpl<>(Arrays.asList(learner));
        when(learnerRepository.findByLevel(level, pageable)).thenReturn(page);

        Page<Learner> result = learnerService.findByLevel(level, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(level, result.getContent().get(0).getLevel());
        verify(learnerRepository).findByLevel(level, pageable);
    }

    @Test
    void findByClassesIsNull() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Learner> page = new PageImpl<>(Arrays.asList(learner));
        when(learnerRepository.findByClassesIsNull(pageable)).thenReturn(page);

        Page<Learner> result = learnerService.findByClassesIsNull(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(learnerRepository).findByClassesIsNull(pageable);
    }
} 