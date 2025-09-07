package com.webknot.campus.eventmanagement.dto;

import lombok.Data;

@Data
public class RegistrationResponse {
    private Long registrationId;
    private Long studentId;
    private String studentName;
    private Long eventId;
    private String eventName;
    private boolean attended;
}
