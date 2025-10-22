# Task Management System — Backend with Spring Boot

A **backend application** developed in **Java (Spring Boot)** for a full-stack **Task Management System**.  
The backend is responsible for handling all task-related logic, data persistence and REST API communication.  
The **frontend**, built separately using **React + Vite**, consumes this API to provide a user-friendly interface.

## Overview

The system allows users to manage **task lists** and **tasks**, supporting the full set of **CRUD operations**.  
It includes robust error handling, DTO-based data transfer and clear separation between layers.

The application is designed to be built to grow and run anywhere in **Docker**, using **PostgreSQL** as the main database.

## Technologies Used

- **Java 17** — Core language  
- **Spring Boot** — REST API framework  
- **Spring Data JPA** — Object-relational mapping and repository pattern  
- **PostgreSQL** — Relational database  
- **Docker** — Containerization for easy deployment  
- **Maven** — Build automation and dependency management  

## Architecture

The backend follows a **layered architecture**, promoting modularity and maintainability:

- **Controller Layer** - Defines RESTful endpoints for tasks and task lists  
- **Service Layer** - Contains the business logic and validation  
- **Repository Layer** - Handles database access using **Spring Data JPA**  

Additional features:
- **DTOs and Mappers** for clean API–entity communication  
- **Optional handling** to prevent `NullPointerExceptions`  
- **Exception management** with custom error responses  

## Data Model

The core of the system revolves around two entities:

- **TaskList** → Represents a collection of related tasks  
- **Task** → Represents an individual task (title, description, completion status, etc.)

The relationship between them is **One-to-Many** (one TaskList contains many Tasks).  
Database persistence is managed with **JPA** and **PostgreSQL**.

## API Endpoints

Below is a summary of the main REST endpoints exposed by the backend:

### Task Lists
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/api/tasklists` | Retrieve all task lists |
| `GET` | `/api/tasklists/{id}` | Retrieve a specific task list |
| `POST` | `/api/tasklists` | Create a new task list |
| `PUT` | `/api/tasklists/{id}` | Update an existing task list |
| `DELETE` | `/api/tasklists/{id}` | Delete a task list |

### Tasks
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/api/tasks` | Retrieve all tasks |
| `GET` | `/api/tasks/{id}` | Retrieve a specific task |
| `POST` | `/api/tasks` | Create a new task |
| `PUT` | `/api/tasks/{id}` | Update an existing task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |

Each task is linked to a task list via a **foreign key**.


## Example Request
POST /api/tasks
```json
{
  "title": "Finish documentation",
  "description": "Complete the project README and API reference",
  "completed": false,
  "taskListId": 1
}
```
Response:
```json
{
  "id": 42,
  "title": "Finish documentation",
  "description": "Complete the project README and API reference",
  "completed": false,
  "taskListId": 1
}
```

