package com.example.dexters_formation.service.interfaces;

import com.example.dexters_formation.entity.Learner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface LearnerService {
    Learner create(Learner learner);
    List<Learner> getAll();
    Learner getById(UUID id);
    Learner update(UUID id, Learner learner);
    void delete(UUID id);
    
    List<Learner> findByLastName(String lastName);
    List<Learner> findByFirstNameAndLastName(String firstName, String lastName);
    List<Learner> findByEmailContaining(String emailDomain);
    List<Learner> findLearnersInClass(UUID classId);
    List<Learner> findLearnersByLevelAndProgram(String level, UUID programId);
    
    // Pagination methods
    Page<Learner> findByLevel(String level, Pageable pageable);
    Page<Learner> findByClassesIsNull(Pageable pageable);
}
