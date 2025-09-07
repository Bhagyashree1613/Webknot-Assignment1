package com.webknot.campus.eventmanagement.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EventRequest {
    private String title;
    private String type;
    private LocalDate date;
    private Long collegeId; // just the ID
}
