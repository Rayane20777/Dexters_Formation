package com.example.dexters_formation.unit;

import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.entity.Instructor;
import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.repository.ClassesRepository;
import com.example.dexters_formation.service.ClassesServiceImpl;
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
class ClassesServiceImplTest {

    @Mock
    private ClassesRepository classesRepository;

    @InjectMocks
    private ClassesServiceImpl classesService;

    private Classes classes;
    private UUID classId;
    private Program program;
    private Instructor instructor;

    @BeforeEach
    void setUp() {
        classId = UUID.randomUUID();
        program = new Program();
        program.setId(UUID.randomUUID());
        
        instructor = new Instructor();
        instructor.setId(UUID.randomUUID());
        
        classes = new Classes();
        classes.setId(classId);
        classes.setName("Java Fundamentals");
        classes.setRoomNumber(12);
        classes.setProgram(program);
        classes.setInstructor(instructor);
    }

    @Test
    void create() {
        when(classesRepository.save(classes)).thenReturn(classes);
        
        Classes result = classesService.create(classes);
        
        assertNotNull(result);
        assertEquals(classes.getName(), result.getName());
        verify(classesRepository).save(classes);
    }

    @Test
    void getAll() {
        List<Classes> classesList = Arrays.asList(classes);
        when(classesRepository.findAll()).thenReturn(classesList);

        List<Classes> result = classesService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(classes.getName(), result.get(0).getName());
        verify(classesRepository).findAll();
    }

    @Test
    void getById() {
        when(classesRepository.findById(classId)).thenReturn(Optional.of(classes));

        Optional<Classes> result = classesService.getById(classId);

        assertTrue(result.isPresent());
        assertEquals(classes.getName(), result.get().getName());
        verify(classesRepository).findById(classId);
    }

    @Test
    void update() {
        when(classesRepository.existsById(classId)).thenReturn(true);
        when(classesRepository.save(classes)).thenReturn(classes);

        Classes result = classesService.update(classId, classes);

        assertNotNull(result);
        assertEquals(classes.getName(), result.getName());
        verify(classesRepository).existsById(classId);
        verify(classesRepository).save(classes);
    }

    @Test
    void delete() {
        when(classesRepository.existsById(classId)).thenReturn(true);

        classesService.delete(classId);

        verify(classesRepository).existsById(classId);
        verify(classesRepository).deleteById(classId);
    }

    @Test
    void findByProgramId() {
        List<Classes> classesList = Arrays.asList(classes);
        when(classesRepository.findByProgramId(program.getId())).thenReturn(classesList);

        List<Classes> result = classesService.findByProgramId(program.getId());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(program.getId(), result.get(0).getProgram().getId());
        verify(classesRepository).findByProgramId(program.getId());
    }

    @Test
    void findClassesWithAvailableSpots() {
        List<Classes> classesList = Arrays.asList(classes);
        when(classesRepository.findClassesWithAvailableSpots(20)).thenReturn(classesList);

        List<Classes> result = classesService.findClassesWithAvailableSpots(20);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(classesRepository).findClassesWithAvailableSpots(20);
    }

    @Test
    void findClassesByInstructorSpeciality() {
        List<Classes> classesList = Arrays.asList(classes);
        when(classesRepository.findClassesByInstructorSpeciality("Java")).thenReturn(classesList);

        List<Classes> result = classesService.findClassesByInstructorSpeciality("Java");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(classesRepository).findClassesByInstructorSpeciality("Java");
    }

    @Test
    void findByNameContaining() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Classes> page = new PageImpl<>(Arrays.asList(classes));
        when(classesRepository.findByNameContaining("Java", pageable)).thenReturn(page);

        Page<Classes> result = classesService.findByNameContaining("Java", pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(classesRepository).findByNameContaining("Java", pageable);
    }

    @Test
    void findByInstructorIsNull() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Classes> page = new PageImpl<>(Arrays.asList(classes));
        when(classesRepository.findByInstructorIsNull(pageable)).thenReturn(page);

        Page<Classes> result = classesService.findByInstructorIsNull(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(classesRepository).findByInstructorIsNull(pageable);
    }
} 