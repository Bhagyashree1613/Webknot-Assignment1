package com.webknot.campus.eventmanagement.controller;

import com.webknot.campus.eventmanagement.dto.AttendanceRequest;
import com.webknot.campus.eventmanagement.dto.RegistrationRequest;
import com.webknot.campus.eventmanagement.dto.RegistrationResponse;
import com.webknot.campus.eventmanagement.model.Registration;
import com.webknot.campus.eventmanagement.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // 1️⃣ Register a student to an event
    @PostMapping
    public RegistrationResponse registerStudent(@RequestBody RegistrationRequest request) {
        Registration registration = registrationService.registerStudent(request.getStudentId(), request.getEventId());
        return mapToResponse(registration);
    }

    // 2️⃣ Mark attendance
    @PutMapping("/{registrationId}/attendance")
    public RegistrationResponse markAttendance(@PathVariable Long registrationId, @RequestBody AttendanceRequest request) {
        Registration registration = registrationService.markAttendanceById(registrationId, request.isAttended());
        return mapToResponse(registration);
    }

    // 3️⃣ Get all registrations for an event
    @GetMapping("/event/{eventId}")
    public List<RegistrationResponse> getRegistrationsByEvent(@PathVariable Long eventId) {
        List<Registration> registrations = registrationService.getRegistrationsByEvent(eventId);
        return registrations.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Helper to map Registration entity to DTO
    private RegistrationResponse mapToResponse(Registration reg) {
        RegistrationResponse resp = new RegistrationResponse();
        resp.setRegistrationId(reg.getId());
        resp.setStudentId(reg.getStudent().getId());
        resp.setStudentName(reg.getStudent().getName());
        resp.setEventId(reg.getEvent().getId());
        resp.setEventName(reg.getEvent().getTitle()); // <-- changed here
        resp.setAttended(reg.isAttended());
        return resp;
    }
}
