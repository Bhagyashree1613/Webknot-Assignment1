package com.webknot.campus.eventmanagement.controller;

import com.webknot.campus.eventmanagement.model.Event;
import com.webknot.campus.eventmanagement.dto.EventRequest;
import com.webknot.campus.eventmanagement.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public Event createEvent(@RequestBody EventRequest request) {
        return eventService.createEvent(request);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

