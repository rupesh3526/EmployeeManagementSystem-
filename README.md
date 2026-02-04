# 🚀 Employee Management System

A Spring Boot backend REST application for managing employees and their skills,
built using Hibernate/JPA, MySQL, and Spring Security with role-based authorization.

This project focuses on clean CRUD design, layered architecture, and secure update
operations (self or admin access).

---

## 📌 Key Features

- Employee CRUD operations
- Fetch employee by ID or email
- Assign and manage skills for employees
- Bulk employee insertion
- Role-based authorization (USER / ADMIN)
- Secure update logic (self or admin only)
- Proper HTTP status codes and exception handling
- DTO-driven request/response handling
- Hibernate + JPA for ORM
- Clean service-layer business logic
- RESTful API architecture

---

## 🔐 Security Overview

- Basic Authentication using Spring Security
- Email is used as the username
- Authorization rules:
  - A user can update **only their own profile**
  - An admin can update **any employee and roles**
- Authorization enforced using `SecurityContextHolder`

---

## 🏗️ Tech Stack

| Layer | Technology |
|------|------------|
| Backend | Spring Boot |
| Security | Spring Security (Basic Auth) |
| ORM | Hibernate / JPA |
| Database | MySQL |
| Build Tool | Maven |
| Logging | SLF4J + Logback |
| Java Version | Java 17+ |

---

## 📡 REST API Endpoints

### ▶️ Employee Operations

| Method | Endpoint | Description |
|------|---------|-------------|
| GET | `/employees` | Fetch all employees |
| GET | `/employees/{id}` | Get employee by ID |
| GET | `/employees/email/{email}` | Get employee by email |
| POST | `/employees` | Create a new employee |
| PUT | `/employees/{id}` | Update employee |
| DELETE | `/employees/{id}` | Delete employee |
| GET | `/employees/count` | Count total employees |
| POST | `/allEmployees` | Bulk insert employees |

---

### ▶️ Skill Operations

| Method | Endpoint | Description |
|------|---------|-------------|
| GET | `/skills/{employeeId}` | Get skills of an employee |

---

## 🗃️ Database Schema (Simplified)

### Employee

| Field | Type |
|------|------|
| id | BIGINT (PK) |
| name | VARCHAR |
| email | VARCHAR (unique) |
| phone | VARCHAR |
| role | VARCHAR |

### Skill

| Field | Type |
|------|------|
| id | BIGINT (PK) |
| skill_name | VARCHAR |
| employee_id | FK → Employee |

---

## ⚙️ How to Run the Project

### 1️⃣ Clone the repository

```bash
git clone https://github.com/rupesh3526/EmployeeManagementSystem-.git
```
2️⃣ Configure Database

Update application.properties:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/ems
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```
3️⃣ Build and Run
```bash
mvn spring-boot:run
```
