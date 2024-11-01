package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Learner;
import com.example.dexters_formation.repository.LearnerRepository;
import com.example.dexters_formation.service.interfaces.LearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return learnerRepository.findById(id).orElse(null);
    }

    @Override
    public Learner update(UUID id, Learner learner) {
        if (learnerRepository.existsById(id)) {
            learner.setId(id);
            return learnerRepository.save(learner);
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        if (learnerRepository.existsById(id)) {
            learnerRepository.deleteById(id);
        }
    }
}
