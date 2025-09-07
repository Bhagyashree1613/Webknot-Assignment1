package com.webknot.campus.eventmanagement.service;

import com.webknot.campus.eventmanagement.dto.FeedbackRequest;
import com.webknot.campus.eventmanagement.dto.FeedbackResponse;
import com.webknot.campus.eventmanagement.model.Feedback;
import com.webknot.campus.eventmanagement.model.Registration;
import com.webknot.campus.eventmanagement.repository.FeedbackRepository;
import com.webknot.campus.eventmanagement.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final RegistrationRepository registrationRepository;

    // Submit feedback
    public FeedbackResponse giveFeedback(FeedbackRequest request) {
        Registration registration = registrationRepository.findById(request.getRegistrationId())
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        Feedback feedback = new Feedback();
        feedback.setRegistration(registration);
        feedback.setRating(request.getRating());
        feedback.setComments(request.getComments());

        Feedback saved = feedbackRepository.save(feedback);

        return mapToResponse(saved);
    }

    // Get all feedback for a specific event
    public List<FeedbackResponse> getFeedbackByEvent(Long eventId) {
        return feedbackRepository.findByEventId(eventId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Map Feedback entity to FeedbackResponse DTO
    private FeedbackResponse mapToResponse(Feedback feedback) {
        FeedbackResponse resp = new FeedbackResponse();
        resp.setId(feedback.getId());
        resp.setRating(feedback.getRating());
        resp.setComments(feedback.getComments());
        resp.setRegistrationId(feedback.getRegistration().getId());
        resp.setStudentId(feedback.getRegistration().getStudent().getId());
        resp.setStudentName(feedback.getRegistration().getStudent().getName());
        resp.setEventId(feedback.getRegistration().getEvent().getId());
        resp.setEventTitle(feedback.getRegistration().getEvent().getTitle());
        return resp;
    }
}
