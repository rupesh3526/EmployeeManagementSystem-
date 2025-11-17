# 🚀 Employee Management System  
A Spring Boot–based backend application to manage employees, skills, and related operations with Hibernate ORM, REST APIs, and MySQL integration.

---

## 📌 Features
- Create, update, delete employees  
- Fetch all employees or get employee by ID  
- Read employee using email  
- Assign and manage skills for employees  
- Bulk employee insertion  
- Proper HTTP status codes for all operations  
- Validation-ready structure with DTOs/entities  
- Hibernate + JPA for database operations  
- Centralized service layer logic  
- Clean REST API architecture

---

## 🏗️ Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Spring Boot  |
| ORM | Hibernate / JPA |
| Database | MySQL / PostgreSQL (configurable) |
| Validation | Jakarta Validation / @Valid |
| Build Tool | Maven |
| Logging | SLF4J + Logback |
| Java Version | Java 17+ |

---

## 🔧 Project Structure

---

## 📡 REST API Endpoints

### ▶️ **Employee Operations**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/employees` | Fetch all employees |
| GET | `/employees/{id}` | Get employee by ID |
| GET | `/employeess/{email}` | Get employee by email |
| POST | `/employees` | Create a new employee |
| PUT | `/employees/{id}` | Update employee |
| DELETE | `/employees/{id}` | Delete employee |
| GET | `/count` | Count total employees |
| POST | `/allEmployees` | Bulk insert employees |

---

### ▶️ **Skill Operations**

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/skills/{id}` | Get skills of employee by ID |

---

## 🗃️ Database Schema (Example)

### `Employee`
| Field | Type |
|-------|------|
| id | BIGINT (PK) |
| name | VARCHAR |
| email | VARCHAR (unique) |
| phone | VARCHAR |
| … |

### `Skill`
| Field | Type |
|-------|------|
| id | BIGINT (PK) |
| skillName | VARCHAR |
| employee_id | FK to Employee |

---

## ⚙️ How to Run the Project

### 1️⃣ Clone the repo
```bash
git clone https://github.com/rupesh3526/EmployeeManagementSystem-.git
```

### 2️⃣ Configure your DB

In application.properties:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/ems
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

3️⃣ Build & run
```bash
mvn spring-boot:run
```


