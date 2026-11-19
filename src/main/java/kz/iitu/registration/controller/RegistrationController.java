
package kz.iitu.registration.controller;

import kz.iitu.registration.model.EventInfo;
import kz.iitu.registration.service.EventService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final EventService eventService;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.course}")
    private String course;

    @Value("${app.semester}")
    private String semester;

    public RegistrationController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", eventService.getWelcomeMessage()
        );
    }

    @GetMapping("/events")
    public List<EventInfo> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/info")
    public Map<String, String> getInfo() {
        return Map.of(
                "application", appName,
                "course", course,
                "semester", semester
        );
    }

    @GetMapping("/events/{id}")
    public Object getEventById(@PathVariable int id) {

        for (EventInfo event : eventService.getEvents()) {
            if (event.getId() == id) {
                return event;
            }
        }

        return org.springframework.http.ResponseEntity
                .status(404)
                .body(Map.of("error", "Event not found"));
    }
}