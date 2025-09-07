package com.webknot.campus.eventmanagement.dto;

import lombok.Data;

@Data
public class FeedbackRequest {
    private Long registrationId;
    private int rating;  // 1-5
    private String comments; // optional
}
