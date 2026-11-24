
package kz.iitu.registration.controller;

import kz.iitu.registration.model.EventInfo;
import kz.iitu.registration.service.EventService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    // Подключаем сервис через конструктор
    public RegistrationController(EventService eventService) {
        this.eventService = eventService;
    }

    // Приветствие из Lab 6
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", eventService.getWelcomeMessage()
        );
    }

    // Информация о приложении из Lab 6
    @GetMapping("/info")
    public Map<String, String> getInfo() {
        return Map.of(
                "application", appName,
                "course", course,
                "semester", semester
        );
    }

    // GET: все мероприятия или поиск по названию
    @GetMapping("/events")
    public List<EventInfo> getEvents(
            @RequestParam(required = false) String title) {

        if (title != null) {
            return eventService.searchByTitle(title);
        }

        return eventService.getEvents();
    }

    // GET: поиск мероприятия по ID
    @GetMapping("/events/{id}")
    public ResponseEntity<EventInfo> getEventById(
            @PathVariable int id) {

        EventInfo event = eventService.getEventById(id);

        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(event);
    }

    // POST: создание нового мероприятия
    @PostMapping("/events")
    public ResponseEntity<EventInfo> addEvent(
            @RequestBody EventInfo request) {

        if (!isValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        EventInfo event = eventService.addEvent(
                request.getTitle(),
                request.getLocation()
        );

        return ResponseEntity.status(201).body(event);
    }

    // PUT: изменение мероприятия
    @PutMapping("/events/{id}")
    public ResponseEntity<EventInfo> updateEvent(
            @PathVariable int id,
            @RequestBody EventInfo request) {

        EventInfo existing = eventService.getEventById(id);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (!isValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        EventInfo updated = eventService.updateEvent(
                id,
                request.getTitle(),
                request.getLocation()
        );

        return ResponseEntity.ok(updated);
    }

    // DELETE: удаление мероприятия
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable int id) {

        boolean deleted = eventService.deleteEvent(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Проверка названия и места проведения
    private boolean isValid(EventInfo event) {
        return event != null
                && event.getTitle() != null
                && !event.getTitle().isBlank()
                && event.getLocation() != null
                && !event.getLocation().isBlank();
    }
}
