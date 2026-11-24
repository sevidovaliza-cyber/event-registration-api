
# Event Registration API

## Laboratory Work 7
Spring Boot REST Controller — CRUD Operations

## Description
A REST API for managing university events.
The application supports creating, reading, updating,
deleting and searching events.

## Technologies
- Java 21
- Spring Boot
- Gradle
- Postman

## How to Run

Run the following command:

    ./gradlew bootRun

For Windows:

    .\gradlew.bat bootRun

Base URL: http://localhost:9090

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/hello | Welcome message |
| GET | /api/info | Application information |
| GET | /api/events | Get all events |
| GET | /api/events/1 | Get event by ID |
| GET | /api/events?title=quiz | Search by title |
| POST | /api/events | Create an event |
| PUT | /api/events/4 | Update an event |
| DELETE | /api/events/4 | Delete an event |

## JSON Request Example

    {
      "title": "Python Workshop",
      "location": "Room 305"
    }

## HTTP Status Codes

- 200 OK
- 201 Created
- 204 No Content
- 400 Bad Request
- 404 Not Found

## Data Storage

Events are stored in memory using ArrayList.
IDs are assigned automatically by the server.
All changes are reset after restarting the application.

## Conclusion

The application implements REST CRUD operations
using Spring Boot, constructor dependency injection,
request parameters, path variables and JSON request bodies.
