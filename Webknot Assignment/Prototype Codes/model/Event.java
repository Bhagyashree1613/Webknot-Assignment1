package com.webknot.campus.eventmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String type; // Workshop / Hackathon / Tech Talk / Fest

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "college_id")
    @JsonBackReference // Event belongs to a College
    private College college;

    // An Event can have many registrations
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations;
}
