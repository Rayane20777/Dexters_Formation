package com.example.dexters_formation.unit;

import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.repository.InstructorRepository;
import com.example.dexters_formation.service.InstructorServiceImpl;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServiceImpl instructorService;

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

        Optional<Instructor> result = instructorService.getById(instructorId);

        assertNotNull(result);
        assertEquals(instructor.getFirstName(), result.get().getFirstName());
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

    @Test
    void delete() {
        when(instructorRepository.existsById(instructorId)).thenReturn(true);

        instructorService.delete(instructorId);

        verify(instructorRepository).existsById(instructorId);
        verify(instructorRepository).deleteById(instructorId);
    }

    @Test
    void findBySpeciality() {
        String speciality = "Java";
        List<Instructor> instructors = Arrays.asList(instructor);
        when(instructorRepository.findBySpeciality(speciality)).thenReturn(instructors);

        List<Instructor> result = instructorService.findBySpeciality(speciality);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(speciality, result.get(0).getSpeciality());
        verify(instructorRepository).findBySpeciality(speciality);
    }

    @Test
    void findByLastNameStartingWith() {
        String prefix = "Do";
        List<Instructor> instructors = Arrays.asList(instructor);
        when(instructorRepository.findByLastNameStartingWith(prefix)).thenReturn(instructors);

        List<Instructor> result = instructorService.findByLastNameStartingWith(prefix);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getLastName().startsWith(prefix));
        verify(instructorRepository).findByLastNameStartingWith(prefix);
    }

    @Test
    void findAvailableInstructors() {
        List<Instructor> instructors = Arrays.asList(instructor);
        when(instructorRepository.findAvailableInstructors()).thenReturn(instructors);

        List<Instructor> result = instructorService.findAvailableInstructors();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(instructorRepository).findAvailableInstructors();
    }

    @Test
    void findBySpecialities() {
        List<String> specialities = Arrays.asList("Java", "Spring");
        List<Instructor> instructors = Arrays.asList(instructor);
        when(instructorRepository.findBySpecialities(specialities)).thenReturn(instructors);

        List<Instructor> result = instructorService.findBySpecialities(specialities);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(instructorRepository).findBySpecialities(specialities);
    }

    @Test
    void findBySpecialityContaining() {
        String specialityPart = "Java";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Instructor> page = new PageImpl<>(Arrays.asList(instructor));
        when(instructorRepository.findBySpecialityContaining(specialityPart, pageable))
            .thenReturn(page);

        Page<Instructor> result = instructorService.findBySpecialityContaining(specialityPart, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(instructorRepository).findBySpecialityContaining(specialityPart, pageable);
    }

    @Test
    void findByClassesIsNotNull() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Instructor> page = new PageImpl<>(Arrays.asList(instructor));
        when(instructorRepository.findByClassesIsNotNull(pageable)).thenReturn(page);

        Page<Instructor> result = instructorService.findByClassesIsNotNull(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(instructorRepository).findByClassesIsNotNull(pageable);
    }
} 