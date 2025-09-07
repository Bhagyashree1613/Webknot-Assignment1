package com.webknot.campus.eventmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating; // 1–5

    private String comments;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
