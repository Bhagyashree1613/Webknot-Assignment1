package com.webknot.campus.eventmanagement.service;


import com.webknot.campus.eventmanagement.model.Event;
import com.webknot.campus.eventmanagement.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import com.webknot.campus.eventmanagement.repository.CollegeRepository;
import com.webknot.campus.eventmanagement.dto.EventRequest;
import com.webknot.campus.eventmanagement.model.College;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final CollegeRepository collegeRepository;

    public Event createEvent(EventRequest request) {
        College college = collegeRepository.findById(request.getCollegeId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setType(request.getType());
        event.setDate(request.getDate());
        event.setCollege(college);

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }
}
