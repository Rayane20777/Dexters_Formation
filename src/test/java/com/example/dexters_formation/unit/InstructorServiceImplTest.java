package com.example.dexters_formation.unit;

import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.repository.InstructorRepository;
import com.example.dexters_formation.service.InstructorServiceImp;
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
public class InstructorServiceImplTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServiceImp instructorService;

    private Instructor instructor;
    private UUID instructorId;

    @BeforeEach
    void setUp() {
        instructorId = UUID.randomUUID();
        instructor = new Instructor();
        instructor.setId(instructorId);
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setEmail("john.doe@example.com");
        instructor.setSpeciality("Java");
    }

    @Test
    void create() {
        when(instructorRepository.save(instructor)).thenReturn(instructor);
        
        Instructor result = instructorService.create(instructor);
        
        assertNotNull(result);
        assertEquals(instructor.getFirstName(), result.getFirstName());
        assertEquals(instructor.getSpeciality(), result.getSpeciality());
        verify(instructorRepository).save(instructor);
    }

    @Test
    void getAll() {
        List<Instructor> instructors = Arrays.asList(instructor);
        when(instructorRepository.findAll()).thenReturn(instructors);

        List<Instructor> result = instructorService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(instructor.getFirstName(), result.get(0).getFirstName());
        verify(instructorRepository).findAll();
    }

    @Test
    void getById() {
        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));

        Instructor result = instructorService.getById(instructorId);

        assertNotNull(result);
        assertEquals(instructor.getFirstName(), result.getFirstName());
        verify(instructorRepository).findById(instructorId);
    }
    
    @Test
    void update() {
        when(instructorRepository.existsById(instructorId)).thenReturn(true);
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor result = instructorService.update(instructorId, instructor);

        assertNotNull(result);
        assertEquals(instructor.getFirstName(), result.getFirstName());
        verify(instructorRepository).existsById(instructorId);
        verify(instructorRepository).save(instructor);
    }


} 