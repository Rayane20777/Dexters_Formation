package com.example.dexters_formation.unit;

import com.example.dexters_formation.entity.Program;
import com.example.dexters_formation.repository.ProgramRepository;
import com.example.dexters_formation.service.ProgramServiceImpl;
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
public class ProgramServiceImplTest {

    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private ProgramServiceImpl programService;

    private Program program;
    private UUID programId;

    @BeforeEach
    void setUp() {
        programId = UUID.randomUUID();
        program = new Program();
        program.setId(programId);
        program.setTitle("Test");
        program.setLevel(1);
        program.setPrerequisites("Test");
        program.setMinCapacity(5);
        program.setMaxCapacity(10);
    }


    @Test
    void create(){
        when(programRepository.save(program)).thenReturn(program);
        Program result = programService.create(program);

        assertNotNull(result);
        assertEquals(program.getTitle(), result.getTitle());
        verify(programRepository).save(program);
    }

    @Test
    void getAll(){
        List<Program> programs = Arrays.asList(program);
        when(programRepository.findAll()).thenReturn(programs);

        List<Program> result = programService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(program.getTitle(), result.get(0).getTitle());
        verify(programRepository).findAll();
    }

    @Test
    void getById(){
        when(programRepository.findById(programId)).thenReturn(java.util.Optional.ofNullable(program));

        Optional<Program> result = programService.getById(programId);

        assertTrue(result.isPresent());
        assertEquals(program.getTitle(), result.get().getTitle());
        verify(programRepository).findById(programId);
    }

    @Test
    void update() {
        when(programRepository.existsById(programId)).thenReturn(true);
        when(programRepository.save(program)).thenReturn(program);

        Program result = programService.update(programId, program);

        assertNotNull(result);
        assertEquals(program.getTitle(), result.getTitle());
        verify(programRepository).existsById(programId);
        verify(programRepository).save(program);
    }

    @Test
    void delete() {
        when(programRepository.existsById(programId)).thenReturn(true);

        programService.delete(programId);

        verify(programRepository).existsById(programId);
        verify(programRepository).deleteById(programId);
    }
}
