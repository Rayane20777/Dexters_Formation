package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Learner;
import com.example.dexters_formation.repository.LearnerRepository;
import com.example.dexters_formation.service.interfaces.LearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LearnerServiceImpl implements LearnerService {
    private final LearnerRepository learnerRepository;

    @Override
    public Learner create(Learner learner) {
        return learnerRepository.save(learner);
    }

    @Override
    public List<Learner> getAll() {
        return learnerRepository.findAll();
    }

    @Override
    public Learner getById(UUID id) {
        return learnerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Learner not found"));
    }

    @Override
    public Learner update(UUID id, Learner learner) {
        if (learnerRepository.existsById(id)) {
            learner.setId(id);
            return learnerRepository.save(learner);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Learner not found");
    }

    @Override
    public void delete(UUID id) {
        if (!learnerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Learner not found");
        }
        learnerRepository.deleteById(id);
    }

    @Override
    public List<Learner> findByLastName(String lastName) {
        return learnerRepository.findByLastName(lastName);
    }

    @Override
    public List<Learner> findByFirstNameAndLastName(String firstName, String lastName) {
        return learnerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Learner> findByEmailContaining(String emailDomain) {
        return learnerRepository.findByEmailContaining(emailDomain);
    }

    @Override
    public List<Learner> findLearnersInClass(UUID classId) {
        return learnerRepository.findLearnersInClass(classId);
    }

    @Override
    public List<Learner> findLearnersByLevelAndProgram(String level, UUID programId) {
        return learnerRepository.findLearnersByLevelAndProgram(level, programId);
    }

    @Override
    public Page<Learner> findByLevel(String level, Pageable pageable) {
        return learnerRepository.findByLevel(level, pageable);
    }

    @Override
    public Page<Learner> findByClassesIsNull(Pageable pageable) {
        return learnerRepository.findByClassesIsNull(pageable);
    }
}
