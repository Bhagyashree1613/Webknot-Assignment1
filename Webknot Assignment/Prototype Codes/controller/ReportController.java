package com.webknot.campus.eventmanagement.controller;

import com.webknot.campus.eventmanagement.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // 1️⃣ Total registrations per event
    @GetMapping("/registrations-per-event")
    public List<Map<String, Object>> totalRegistrationsPerEvent() {
        return reportService.totalRegistrationsPerEvent();
    }

    // 2️⃣ Attendance percentage per event
    @GetMapping("/attendance-percentage")
    public List<Map<String, Object>> attendancePercentagePerEvent() {
        return reportService.attendancePercentagePerEvent();
    }

    // 3️⃣ Average feedback score per event
    @GetMapping("/average-feedback")
    public List<Map<String, Object>> averageFeedbackPerEvent() {
        return reportService.averageFeedbackPerEvent();
    }

    // 4️⃣ Event popularity report (sorted by registrations)
    @GetMapping("/event-popularity")
    public List<Map<String, Object>> eventPopularityReport() {
        return reportService.eventPopularityReport();
    }

    // 5️⃣ Student participation report (events attended per student)
    @GetMapping("/student-participation")
    public List<Map<String, Object>> studentParticipationReport() {
        return reportService.studentParticipationReport();
    }
}
