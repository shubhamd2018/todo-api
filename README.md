# 🚀 Todo API

A RESTful Todo API built using **Spring Boot** to learn enterprise backend development concepts and best practices. This project demonstrates building scalable REST APIs using layered architecture, Spring Data JPA, Hibernate, and relational database concepts.

---

## 📌 Project Overview

The application provides APIs to manage **Users** and **Todos**. It follows a clean architecture using **Controller → Service → Repository** layers with DTOs, validation, exception handling, and Hibernate ORM.

The project was built while learning how enterprise Spring Boot applications are developed.

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 4
- Spring Data JPA
- Hibernate ORM
- Maven
- H2 Database
- Lombok (Partially Integrated)
- Git & GitHub
- Postman

---

## 📂 Project Structure

```
src/main/java
│
├── controller
│     ├── TodoController
│     └── UserController
│
├── service
│     ├── TodoService
│     └── UserService
│
├── repository
│     ├── TodoRepository
│     └── UserRepository
│
├── entity
│     ├── Todo
│     └── User
│
├── dto
│     ├── request
│     └── response
│
├── exception
│
└── TodoApiApplication
```

---

## ✨ Features Implemented

### ✅ User Module

- Create User
- Get All Users
- Get User By Id

### ✅ Todo Module

- Create Todo
- Get All Todos
- Get Todo By Id
- Update Todo
- Delete Todo

### ✅ Validation

- Request validation using Bean Validation
- Custom validation messages
- Email validation
- Required field validation

### ✅ Exception Handling

- Global Exception Handler
- Todo Not Found Exception
- User Not Found Exception
- Custom Error Response DTO

### ✅ Spring Data JPA

- Entity Mapping
- Repository Pattern
- One-To-Many & Many-To-One Relationships
- JPQL Queries
- Derived Query Methods

### ✅ Hibernate Concepts

- Persistence Context
- Dirty Checking
- Transactions
- Lazy Loading
- N+1 Query Problem
- JOIN FETCH Optimization

### ✅ Additional Features

- Pagination
- Sorting
- DTO Mapping
- Layered Architecture
- H2 Database Integration

---

## 📡 REST APIs

### User APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/users` | Create User |
| GET | `/users` | Get All Users |
| GET | `/users/{id}` | Get User By Id |

---

### Todo APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/todos` | Create Todo |
| GET | `/todos` | Get All Todos |
| GET | `/todos/{id}` | Get Todo By Id |
| PUT | `/todos/{id}` | Update Todo |
| DELETE | `/todos/{id}` | Delete Todo |
| GET | `/todos/page` | Pagination & Sorting |

---

## 🗄️ Database

The project currently uses **H2 In-Memory Database** for development.

Entities:

- User
- Todo

Relationship:

```
User (1)
   │
   │
   ▼
Todo (Many)
```

---

## ▶️ Running the Project

Clone the repository

```bash
git clone https://github.com/shubhamd2018/todo-api.git
```

Navigate into the project

```bash
cd todo-api
```

Run the application

```bash
./mvnw spring-boot:run
```

Application starts on

```
http://localhost:9090
```

H2 Console

```
http://localhost:9090/h2-console
```

---

## 📖 Key Concepts Learned

- Spring Boot Architecture
- Dependency Injection
- REST API Development
- DTO Pattern
- Validation
- Global Exception Handling
- Spring Data JPA
- Hibernate ORM
- Entity Relationships
- Transactions
- JPQL
- Pagination
- Sorting
- Lazy Loading
- JOIN FETCH
- Solving N+1 Query Problem

---

## 🚀 Future Improvements

- Spring Security
- JWT Authentication
- Role-Based Authorization
- PostgreSQL Integration
- Docker
- Unit Testing
- Swagger/OpenAPI Documentation
- Deployment

---

## 👨‍💻 Author

**Shubham Dambal**

GitHub: https://github.com/shubhamd2018
