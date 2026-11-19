
# Event Registration API

## Laboratory Work 6
Introduction to Spring Boot and Dependency Injection

## Project Description
This project is a simple Spring Boot backend application
for an Event Registration system.

The application provides REST API endpoints that return
JSON responses. Event information is stored in memory.

## Technologies
- Java 21
- Spring Boot 4.1.2
- Gradle
- Spring Web MVC

## Project Structure
- controller - handles HTTP requests
- service - contains application logic and event data
- model - represents event information
- application.properties - stores application settings

## API Endpoints

GET /api/hello
Returns a welcome message.

GET /api/events
Returns a list of three events.

GET /api/info
Returns application configuration.

GET /api/events/{id}
Returns an event by ID or a 404 error if not found.

## How to Run

1. Open the project in IntelliJ IDEA.
2. Make sure JDK 21 is installed.
3. Open the terminal in the project directory.
4. Run the command:

   ./gradlew bootRun

   On Windows:

   .\gradlew.bat bootRun

5. Open http://localhost:8080/api/events

## Dependency Injection

RegistrationController receives EventService through
its constructor. Spring Boot automatically creates and
injects the service object using the @Service annotation.

## Database

This application does not use a database.
All events are stored in an in-memory list.
