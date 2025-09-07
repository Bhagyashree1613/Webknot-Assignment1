package com.webknot.campus.eventmanagement.service;

import com.webknot.campus.eventmanagement.model.Event;
import com.webknot.campus.eventmanagement.model.Student;
import com.webknot.campus.eventmanagement.repository.EventRepository;
import com.webknot.campus.eventmanagement.repository.FeedbackRepository;
import com.webknot.campus.eventmanagement.repository.RegistrationRepository;
import com.webknot.campus.eventmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;
    private final FeedbackRepository feedbackRepository;

    // 1️⃣ Total registrations per event
    public List<Map<String, Object>> totalRegistrationsPerEvent() {
        List<Object[]> results = registrationRepository.countRegistrationsPerEvent();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Long eventId = (Long) row[0];
            Long count = (Long) row[1];
            Event event = eventRepository.findById(eventId).orElseThrow();
            Map<String, Object> map = new HashMap<>();
            map.put("eventId", eventId);
            map.put("eventTitle", event.getTitle());
            map.put("totalRegistrations", count);
            response.add(map);
        }

        return response;
    }

    // 2️⃣ Attendance percentage per event
    public List<Map<String, Object>> attendancePercentagePerEvent() {
        List<Object[]> results = registrationRepository.attendanceStatsPerEvent();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Long eventId = (Long) row[0];
            Long total = (Long) row[1];
            Long attended = (Long) row[2];
            Event event = eventRepository.findById(eventId).orElseThrow();

            Map<String, Object> map = new HashMap<>();
            map.put("eventId", eventId);
            map.put("eventTitle", event.getTitle());
            map.put("attendancePercentage", (attended * 100.0) / total);
            response.add(map);
        }

        return response;
    }

    // 3️⃣ Average feedback score per event
    public List<Map<String, Object>> averageFeedbackPerEvent() {
        List<Object[]> results = feedbackRepository.avgFeedbackPerEvent();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Long eventId = (Long) row[0];
            Double avgRating = (Double) row[1];
            Event event = eventRepository.findById(eventId).orElseThrow();

            Map<String, Object> map = new HashMap<>();
            map.put("eventId", eventId);
            map.put("eventTitle", event.getTitle());
            map.put("averageRating", avgRating);
            response.add(map);
        }

        return response;
    }

    // 4️⃣ Event popularity report (sorted by registrations)
    public List<Map<String, Object>> eventPopularityReport() {
        List<Map<String, Object>> events = totalRegistrationsPerEvent();
        events.sort((a, b) -> Long.compare((Long) b.get("totalRegistrations"), (Long) a.get("totalRegistrations")));
        return events;
    }

    // 5️⃣ Student participation report (events attended per student)
    public List<Map<String, Object>> studentParticipationReport() {
        List<Object[]> results = registrationRepository.eventsAttendedPerStudent();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {
            Long studentId = (Long) row[0];
            Long attendedCount = (Long) row[1];
            Student student = studentRepository.findById(studentId).orElseThrow();

            Map<String, Object> map = new HashMap<>();
            map.put("studentId", studentId);
            map.put("studentName", student.getName());
            map.put("eventsAttended", attendedCount);
            response.add(map);
        }

        return response;
    }
}
