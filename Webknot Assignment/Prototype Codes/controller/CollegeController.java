package com.webknot.campus.eventmanagement.controller;

import com.webknot.campus.eventmanagement.model.College;
import com.webknot.campus.eventmanagement.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colleges")
@RequiredArgsConstructor
public class CollegeController {

    private final CollegeRepository collegeRepository;

    @PostMapping
    public College createCollege(@RequestBody College college) {
        // save college to DB (example)
        return collegeRepository.save(college);
    }

    @GetMapping
    public List<College> getColleges() {
        return collegeRepository.findAll();
    }
}
