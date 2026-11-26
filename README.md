# Event Registration API

## Laboratory Work 8
Spring Data JPA and PostgreSQL

## Description
A REST API for managing university events.
The application supports creating, reading, updating,
deleting and searching events using a PostgreSQL database.

## Technologies
- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Gradle
- Postman
- pgAdmin 4

## Database Setup

1. Install PostgreSQL and pgAdmin 4.
2. Create a database named event_db.
3. Set the DB_USER and DB_PASSWORD environment variables.
4. Run the application.

Database URL:

    jdbc:postgresql://localhost:5432/event_db

The events table is created automatically by Hibernate.

## Configuration

The application.properties file contains:

    spring.datasource.url=jdbc:postgresql://localhost:5432/event_db
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_PASSWORD}
    spring.jpa.hibernate.ddl-auto=update

The update option is used for development purposes only.
Database credentials are not stored in the repository.

## How to Run

Requirements:
- JDK 21
- Running PostgreSQL server

For Windows PowerShell:

    $env:DB_USER="postgres"
    $env:DB_PASSWORD=Read-Host "PostgreSQL password"
    .\gradlew.bat bootRun

For Linux or macOS:

    export DB_USER=postgres
    read -rs DB_PASSWORD
    export DB_PASSWORD
    ./gradlew bootRun

Base URL: http://localhost:9090

## Application Architecture

Controller -> Service -> Repository -> PostgreSQL

- RegistrationController handles HTTP requests.
- EventService contains business logic.
- EventRepository extends JpaRepository.
- Event represents the database entity.

Constructor dependency injection is used.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/hello | Welcome message |
| GET | /api/info | Application information |
| GET | /api/events | Get all events |
| GET | /api/events/{id} | Get event by ID |
| GET | /api/events?title=quiz | Search by title |
| POST | /api/events | Create an event |
| PUT | /api/events/{id} | Update an event |
| DELETE | /api/events/{id} | Delete an event |

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

## Validation and Search

- Title and location cannot be empty or missing.
- Event IDs are generated automatically.
- Client-provided IDs are ignored when creating events.
- Unknown event IDs return 404 Not Found.
- Search supports partial and case-insensitive matching.
- Search without matching results returns an empty list.

## Data Storage

Events are stored in the PostgreSQL database.

Table: events

| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key |
| title | VARCHAR | Event title |
| location | VARCHAR | Event location |

Data remains available after restarting the application.

## Database Verification

    SELECT id, title, location FROM events;

## Conclusion

The application implements REST CRUD operations
using Spring Boot, Spring Data JPA and PostgreSQL.
The previous ArrayList storage was replaced with
persistent database storage. The application supports
validation, searching, automatic ID generation
and appropriate HTTP status codes.
