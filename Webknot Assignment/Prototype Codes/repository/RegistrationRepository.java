package com.webknot.campus.eventmanagement.repository;

import com.webknot.campus.eventmanagement.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    // All students registered for an event
    List<Registration> findByEventId(Long eventId);

    // All events a student registered for
    List<Registration> findByStudentId(Long studentId);

    // ------------------ Reports ------------------

    // Total registrations per event
    @Query("SELECT r.event.id, COUNT(r.id) FROM Registration r GROUP BY r.event.id")
    List<Object[]> countRegistrationsPerEvent();

    // Attendance stats per event: total registrations and total attended
    @Query("SELECT r.event.id, COUNT(r.id), SUM(CASE WHEN r.attended = true THEN 1 ELSE 0 END) " +
            "FROM Registration r GROUP BY r.event.id")
    List<Object[]> attendanceStatsPerEvent();

    // Number of events attended by each student
    @Query("SELECT r.student.id, COUNT(r.id) FROM Registration r WHERE r.attended = true GROUP BY r.student.id")
    List<Object[]> eventsAttendedPerStudent();
}
