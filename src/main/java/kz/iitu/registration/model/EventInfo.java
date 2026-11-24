
package kz.iitu.registration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventInfo {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    private String title;
    private String location;

    // Пустой конструктор для JSON
    public EventInfo() {
    }

    // Конструктор для создания мероприятия
    public EventInfo(Integer id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}