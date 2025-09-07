package com.webknot.campus.eventmanagement.repository;

import com.webknot.campus.eventmanagement.model.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
    // Optional: Add custom queries if needed
}

