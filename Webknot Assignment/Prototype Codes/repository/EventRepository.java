package com.webknot.campus.eventmanagement.repository;

import com.webknot.campus.eventmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCollegeId(Long collegeId); // Get events of a particular college
}
