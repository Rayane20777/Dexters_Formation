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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.dexters_formation.entity.enums.ProgramStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProgramServiceImplTest {

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
        program.setTitle("Java Bootcamp");
        program.setLevel(2);
        program.setStatus(ProgramStatus.PLANNED);
        program.setStartDate(new Date());
        program.setEndDate(new Date());
        program.setMaxCapacity(30);
    }

    @Test
    void create() {
        when(programRepository.save(program)).thenReturn(program);

        Program result = programService.create(program);

        assertNotNull(result);
        assertEquals(program.getTitle(), result.getTitle());
        assertEquals(program.getLevel(), result.getLevel());
        verify(programRepository).save(program);
    }

    @Test
    void getAll() {
        List<Program> programs = Arrays.asList(program);
        when(programRepository.findAll()).thenReturn(programs);

        List<Program> result = programService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(program.getTitle(), result.get(0).getTitle());
        verify(programRepository).findAll();
    }

    @Test
    void getById() {
        when(programRepository.findById(programId)).thenReturn(Optional.of(program));

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

    @Test
    void findByLevelAndStatus() {
        List<Program> programs = Arrays.asList(program);
        when(programRepository.findByLevelAndStatus(2, ProgramStatus.PLANNED)).thenReturn(programs);

        List<Program> result = programService.findByLevelAndStatus(2, ProgramStatus.PLANNED);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(program.getLevel(), result.get(0).getLevel());
        assertEquals(program.getStatus(), result.get(0).getStatus());
        verify(programRepository).findByLevelAndStatus(2, ProgramStatus.PLANNED);
    }

    @Test
    void findProgramsInDateRange() {
        Date startDate = new Date();
        Date endDate = new Date();
        List<Program> programs = Arrays.asList(program);
        when(programRepository.findProgramsInDateRange(startDate, endDate)).thenReturn(programs);

        List<Program> result = programService.findProgramsInDateRange(startDate, endDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(programRepository).findProgramsInDateRange(startDate, endDate);
    }

    @Test
    void findByStatus() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Program> page = new PageImpl<>(Arrays.asList(program));
        when(programRepository.findByStatus(ProgramStatus.PLANNED, pageable)).thenReturn(page);

        Page<Program> result = programService.findByStatus(ProgramStatus.PLANNED, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(program.getStatus(), result.getContent().get(0).getStatus());
        verify(programRepository).findByStatus(ProgramStatus.PLANNED, pageable);
    }

    @Test
    void findByLevelGreaterThanEqual() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Program> page = new PageImpl<>(Arrays.asList(program));
        when(programRepository.findByLevelGreaterThanEqual(2, pageable)).thenReturn(page);

        Page<Program> result = programService.findByLevelGreaterThanEqual(2, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertTrue(result.getContent().get(0).getLevel() >= 2);
        verify(programRepository).findByLevelGreaterThanEqual(2, pageable);
    }
}
