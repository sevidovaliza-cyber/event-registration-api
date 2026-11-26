package kz.iitu.registration.controller;

import kz.iitu.registration.model.Event;
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

    // Подключение сервиса через конструктор
    public RegistrationController(EventService eventService) {
        this.eventService = eventService;
    }

    // Приветственное сообщение
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", eventService.getWelcomeMessage()
        );
    }

    // Информация о приложении
    @GetMapping("/info")
    public Map<String, String> getInfo() {
        return Map.of(
                "application", appName,
                "course", course,
                "semester", semester
        );
    }

    // Получение всех мероприятий или поиск по названию
    @GetMapping("/events")
    public List<Event> getEvents(
            @RequestParam(required = false) String title) {

        if (title != null) {
            return eventService.searchByTitle(title);
        }

        return eventService.getEvents();
    }

    // Получение мероприятия по ID
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(
            @PathVariable Long id) {

        Event event = eventService.getEventById(id);

        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(event);
    }

    // Создание мероприятия
    @PostMapping("/events")
    public ResponseEntity<Event> addEvent(
            @RequestBody Event request) {

        if (!isValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        Event event = eventService.addEvent(
                request.getTitle(),
                request.getLocation()
        );

        return ResponseEntity.status(201).body(event);
    }

    // Обновление мероприятия
    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestBody Event request) {

        if (eventService.getEventById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        if (!isValid(request)) {
            return ResponseEntity.badRequest().build();
        }

        Event updated = eventService.updateEvent(
                id,
                request.getTitle(),
                request.getLocation()
        );

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // Удаление мероприятия
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable Long id) {

        if (!eventService.deleteEvent(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Проверка названия и места проведения
    private boolean isValid(Event event) {
        return event != null
                && event.getTitle() != null
                && !event.getTitle().isBlank()
                && event.getLocation() != null
                && !event.getLocation().isBlank();
    }
}