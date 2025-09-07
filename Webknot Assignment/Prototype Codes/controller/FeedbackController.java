package com.webknot.campus.eventmanagement.controller;

import com.webknot.campus.eventmanagement.dto.FeedbackRequest;
import com.webknot.campus.eventmanagement.dto.FeedbackResponse;
import com.webknot.campus.eventmanagement.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    // Submit feedback
    @PostMapping
    public FeedbackResponse giveFeedback(@RequestBody FeedbackRequest request) {
        return feedbackService.giveFeedback(request);
    }

    // Get all feedback for an event
    @GetMapping("/event/{eventId}")
    public List<FeedbackResponse> getFeedbackByEvent(@PathVariable Long eventId) {
        return feedbackService.getFeedbackByEvent(eventId);
    }
}
