package com.example.dexters_formation.service;

import com.example.dexters_formation.entity.Learner;
import com.example.dexters_formation.repository.LearnerRepository;
import com.example.dexters_formation.service.interfaces.LearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearnerServiceImpl implements LearnerService {
    private final LearnerRepository learnerRepository;

    @Override
    public Learner create(Learner learner) {
        return learnerRepository.save(learner);
    }
}
