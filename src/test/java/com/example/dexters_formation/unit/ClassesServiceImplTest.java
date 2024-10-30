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

    
} 