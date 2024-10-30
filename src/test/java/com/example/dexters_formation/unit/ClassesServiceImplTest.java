package com.example.dexters_formation.unit;

import com.example.dexters_formation.entity.Classes;
import com.example.dexters_formation.repository.ClassesRepository;
import com.example.dexters_formation.service.ClassesServiceImpl;
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
public class ClassesServiceImplTest {

    @Mock
    private ClassesRepository classesRepository;

    @InjectMocks
    private ClassesServiceImpl classesService;

    private Classes classes;
    private UUID classId;

    @BeforeEach
    void setUp() {
        classId = UUID.randomUUID();
        classes = new Classes();
        classes.setId(classId);
        classes.setName("Java Programming");
        classes.setRoomNumber(101);
    }

    @Test
    void create() {
        when(classesRepository.save(classes)).thenReturn(classes);

        Classes result = classesService.create(classes);

        assertNotNull(result);
        assertEquals(classes.getName(), result.getName());
        assertEquals(classes.getRoomNumber(), result.getRoomNumber());
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

        Classes result = classesService.getById(classId);

        assertNotNull(result);
        assertEquals(classes.getName(), result.getName());
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
} 