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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

}
