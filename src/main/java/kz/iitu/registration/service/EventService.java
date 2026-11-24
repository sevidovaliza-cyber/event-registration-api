
package kz.iitu.registration.service;

import kz.iitu.registration.model.EventInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    // Список мероприятий
    private final List<EventInfo> events = new ArrayList<>();

    // ID для следующего мероприятия
    private int nextId = 4;

    // Начальные мероприятия
    public EventService() {
        events.add(new EventInfo(1, "Web Design Workshop", "Room 101"));
        events.add(new EventInfo(2, "JavaScript Quiz", "Room 202"));
        events.add(new EventInfo(3, "IT Career Talk", "Conference Hall"));
    }

    // Приветственное сообщение из Lab 6
    public String getWelcomeMessage() {
        return "Welcome to the Event Registration API";
    }

    // Получение всех мероприятий
    public List<EventInfo> getEvents() {
        return events;
    }

    // Поиск мероприятия по ID
    public EventInfo getEventById(int id) {
        for (EventInfo event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    // Поиск по названию без учёта регистра
    public List<EventInfo> searchByTitle(String title) {
        List<EventInfo> result = new ArrayList<>();

        for (EventInfo event : events) {
            if (event.getTitle().toLowerCase()
                    .contains(title.toLowerCase())) {
                result.add(event);
            }
        }
        return result;
    }

    // Добавление мероприятия
    public EventInfo addEvent(String title, String location) {
        EventInfo event = new EventInfo(nextId, title, location);
        events.add(event);
        nextId++;
        return event;
    }

    // Изменение мероприятия
    public EventInfo updateEvent(int id, String title, String location) {
        EventInfo event = getEventById(id);

        if (event != null) {
            event.setTitle(title);
            event.setLocation(location);
        }

        return event;
    }

    // Удаление мероприятия
    public boolean deleteEvent(int id) {
        EventInfo event = getEventById(id);

        if (event != null) {
            events.remove(event);
            return true;
        }

        return false;
    }
}
