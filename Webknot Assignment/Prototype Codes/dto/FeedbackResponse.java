package com.webknot.campus.eventmanagement.dto;

import lombok.Data;

@Data
public class FeedbackResponse {
    private Long id;
    private int rating;
    private String comments;
    private Long registrationId;
    private Long studentId;
    private String studentName;
    private Long eventId;
    private String eventTitle;
}
