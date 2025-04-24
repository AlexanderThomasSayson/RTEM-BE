
# RTEM - Real-Time Employee Management System

**RTEM** is a **SaaS-based employee management system** built using **Spring Boot**, **Java 21**, and **Spring Security**. It offers a centralized platform for managing employees, processing payroll, tracking daily time records (DTR), and handling leave applications. Designed with modularity and scalability in mind, RTEM aims to simplify workforce administration for modern businesses.

---

## 🚀 Features

- **Employee Management**: Add, update, and manage employee profiles and user access.
- **Leave Management**: Apply, approve, and track leave requests with detailed history and status.
- **DTR Monitoring**: Real-time logging and tracking of employee time-ins, time-outs, and working hours.
- **Payroll Automation**: Automatically generate and manage payroll based on DTR and leave data.
- **Role-Based Access Control**: Secure routes and data access using roles like Admin, HR, and Employee.
- **JWT-Based Authentication**: Stateless and secure login system.
- **SaaS Ready**: Multi-tenant architecture ready for scaling to multiple organizations.

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Tokens)
- Lombok
- Swagger / OpenAPI
- React / React Native (frontend compatibility)

---

## 📁 Project Structure

```
rtem/
├── common/        # Handles utilities and constants
├── config/        # App configurations and initializers
├── domain/        # Controller, Model, DTO, DAO, Entity, Exceptions, Service
├── security/      # Security and JWT configuration
└── RtemApplication.java
```

---

## 🧪 Running Locally

### Prerequisites

- Java 21
- Maven
- MySQL
- Postman / Swagger-enabled browser

### Steps

```bash
# Clone the repository
git clone https://github.com/AlexanderThomasSayson/RTEM-BE.git
cd RTEM-BE

# Configure src/main/resources/application.properties or application.yml

# Build and run
./mvnw clean install
./mvnw spring-boot:run
```

---

## 🔐 Security

RTEM uses **Spring Security** with **JWT** for stateless authentication. Users log in with credentials to receive a JWT token, which must be provided for all subsequent secured requests. Role-based access ensures that only authorized personnel can access sensitive endpoints.

---

## 📄 Sample API Endpoints

| Method | Endpoint                    | Description                            |
|--------|-----------------------------|----------------------------------------|
| POST   | `/api/v1/auth/login`        | Authenticates user and returns token   |
| POST   | `/api/v1/employees/create`  | Creates a new employee                 |
| GET    | `/api/v1/employees/get-all` | Retrieves all employees                |
| POST   | `/api/v1/leaves/apply`      | Submits a leave request                |
| GET    | `/api/v1/dtr/{employeeId}`  | Gets DTR logs for a specific employee  |
| POST   | `/api/v1/payroll/generate`  | Generates payroll for selected period  |

➡ Full API docs available at: `http://localhost:8084/swagger-ui.html`

---

## 🔄 Microservice & SaaS Integration

RTEM is designed to support integration with:

- **Auth Service** – For centralized authentication (if decoupled)
- **Notification Service** – For sending email/SMS alerts
- **Billing Service** – For handling subscription plans and payments



## 🤝 Contributing

We welcome contributions and feedback!

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m "Add new feature"`)
4. Push to your branch (`git push origin feature/your-feature`)
5. Create a Pull Request

---

## 📬 Contact

For support, collaboration, or onboarding RTEM into your organization, contact the **RTEM Dev Team** at [support@rtem.io](mailto:alexanderthomassayson@gmail.com)
