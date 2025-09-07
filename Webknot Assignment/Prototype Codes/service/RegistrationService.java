package com.webknot.campus.eventmanagement.service;

import com.webknot.campus.eventmanagement.model.Event;
import com.webknot.campus.eventmanagement.model.Registration;
import com.webknot.campus.eventmanagement.model.Student;
import com.webknot.campus.eventmanagement.repository.EventRepository;
import com.webknot.campus.eventmanagement.repository.RegistrationRepository;
import com.webknot.campus.eventmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final EventRepository eventRepository;

    // 1️⃣ Register a student to an event
    public Registration registerStudent(Long studentId, Long eventId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setEvent(event);
        registration.setAttended(false);

        return registrationRepository.save(registration);
    }

    // 2️⃣ Get registrations by event
    public List<Registration> getRegistrationsByEvent(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    // 3️⃣ Get registrations by student
    public List<Registration> getRegistrationsByStudent(Long studentId) {
        return registrationRepository.findByStudentId(studentId);
    }

    // 4️⃣ Mark attendance by registrationId
    public Registration markAttendanceById(Long registrationId, boolean attended) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        registration.setAttended(attended);
        return registrationRepository.save(registration);
    }
}
