# Taskflow API

A Spring Boot REST API for managing projects, tasks, and comments. This backend task management system allows users to organize work through projects, track tasks with various statuses and priorities, and collaborate via comments.

## Features

- Create and manage projects
- Organize tasks within projects
- Add comments to tasks
- Filter and search tasks by status and name
- Track task priority levels (LOW, MEDIUM, HIGH)
- Support for sub-tasks
- Pagination support for task listings

## Tech Stack

- **Framework:** Spring Boot 4.0.2
- **Language:** Java 25
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA / Hibernate
- **Mapping:** MapStruct 1.6.3
- **Validation:** Jakarta Bean Validation
- **Build Tool:** Maven 3

## Project Structure

```
src/main/java/com/chetraseng/taskflow_api/
├── controllers/          # REST API endpoints
├── services/             # Business logic layer
├── repositories/         # Data access layer
├── models/               # JPA entities
├── dto/                  # Request/Response DTOs
├── mappers/              # MapStruct mappers
├── enums/                # TaskStatus, TaskPriority
└── specs/                # JPA Specifications for filtering
```

## Prerequisites

- Java 25
- Maven 3.x
- PostgreSQL 14+

## Configuration

### Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE taskflow_db;
```

### Application Properties

The application uses profile-based configuration. Default development settings (`application-dev.yaml`):

```yaml
spring:
  datasource:
    username: postgres
    password: password
    url: jdbc:postgresql://localhost:5432/taskflow_db
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

Update credentials as needed for your environment.

## Running the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Projects

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/projects` | Create a new project |
| GET | `/api/v1/projects` | List all projects |

### Tasks

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/tasks` | Get all tasks with pagination and filtering |
| GET | `/api/v1/tasks/project/{projectId}` | Get tasks for a specific project |

**Query Parameters for Tasks:**
- `page` - Page number (default: 0)
- `size` - Page size (default: 10)
- `status` - Filter by status (TODO, IN_PROGRESS, DONE)
- `name` - Filter by task name (partial match)

### Comments

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/comments/tasks/{taskId}` | Add a comment to a task |

## Data Models

### Task Status
- `TODO`
- `IN_PROGRESS`
- `DONE`

### Task Priority
- `LOW`
- `MEDIUM`
- `HIGH`

## Request/Response Examples

### Create Project

```bash
POST /api/v1/projects
Content-Type: application/json

{
  "name": "My Project",
  "description": "Project description"
}
```

### Get Tasks with Filtering

```bash
GET /api/v1/tasks?page=0&size=10&status=TODO&name=feature
```

### Add Comment

```bash
POST /api/v1/comments/tasks/1
Content-Type: application/json

{
  "content": "This is a comment"
}
```

## Development

### Building

```bash
mvn clean package
```

### Running Tests

```bash
mvn test
```

## License

This project is for educational purposes.
