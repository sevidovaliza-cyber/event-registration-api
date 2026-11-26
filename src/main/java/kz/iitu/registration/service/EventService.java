package kz.iitu.registration.service;

import kz.iitu.registration.model.Event;
import kz.iitu.registration.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    // Подключение репозитория через конструктор
    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    // Приветственное сообщение
    public String getWelcomeMessage() {
        return "Welcome to the Event Registration API";
    }

    // Получение всех мероприятий
    public List<Event> getEvents() {
        return repository.findAll();
    }

    // Поиск мероприятия по ID
    public Event getEventById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Поиск по названию без учёта регистра
    public List<Event> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    // Добавление мероприятия
    public Event addEvent(String title, String location) {
        Event event = new Event(title, location);
        return repository.save(event);
    }

    // Изменение мероприятия
    public Event updateEvent(Long id, String title, String location) {
        Event event = getEventById(id);

        if (event != null) {
            event.setTitle(title);
            event.setLocation(location);
            return repository.save(event);
        }

        return null;
    }

    // Удаление мероприятия
    public boolean deleteEvent(Long id) {
        Event event = getEventById(id);

        if (event != null) {
            repository.delete(event);
            return true;
        }

        return false;
    }
}