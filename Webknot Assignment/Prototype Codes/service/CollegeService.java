package com.webknot.campus.eventmanagement.service;

import com.webknot.campus.eventmanagement.model.College;
import com.webknot.campus.eventmanagement.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollegeService {
    private final CollegeRepository collegeRepository;

    public Optional<College> getCollegeById(Long id) {
        return collegeRepository.findById(id);
    }
}
