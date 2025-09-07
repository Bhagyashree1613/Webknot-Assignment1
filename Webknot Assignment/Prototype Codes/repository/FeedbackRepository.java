package com.webknot.campus.eventmanagement.repository;

import com.webknot.campus.eventmanagement.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // Get all feedback for a specific event
    @Query("SELECT f FROM Feedback f JOIN f.registration r WHERE r.event.id = :eventId")
    List<Feedback> findByEventId(Long eventId);

    // Average feedback rating per event
    @Query("SELECT r.event.id, AVG(f.rating) FROM Feedback f JOIN f.registration r GROUP BY r.event.id")
    List<Object[]> avgFeedbackPerEvent();
}
