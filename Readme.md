# Leads API

A **Reactive RESTful microservice** for lead management, built with Spring Boot WebFlux. Part of the CRM backend system — handles lead creation, tracking, assignment, and status updates.

---

## ✨ Features

- ⚡ **Reactive & non-blocking** — built with Spring WebFlux for high throughput
- 📋 **Lead lifecycle management** — create, assign, update status, close
- 🔐 **Secured endpoints** — JWT-based authentication
- 🔁 **Resilient** — retry logic and error handling for downstream failures
- 📦 **Containerised** — Docker-ready with CI/CD via GitHub Actions

---

## 🛠️ Tech Stack

| Layer       | Technology                            |
|-------------|---------------------------------------|
| Language    | Java 17                               |
| Framework   | Spring Boot, Spring WebFlux (Reactive)|
| Build Tool  | Gradle                                |
| Auth        | JWT                                   |
| DevOps      | Docker, GitHub Actions                |

---

## 📡 API Endpoints

| Method | Endpoint              | Description               |
|--------|-----------------------|---------------------------|
| GET    | `/api/leads`          | Get all leads             |
| GET    | `/api/leads/{id}`     | Get lead by ID            |
| POST   | `/api/leads`          | Create a new lead         |
| PUT    | `/api/leads/{id}`     | Update lead details       |
| PATCH  | `/api/leads/{id}/status` | Update lead status     |
| DELETE | `/api/leads/{id}`     | Delete a lead             |

---

## 🚀 Getting Started

**Prerequisites:** Java 17+, Gradle

```bash
# 1. Clone the repository
git clone https://github.com/Ajay-Kumar-13/Leads-API.git
cd Leads-API

# 2. Build the project
./gradlew build

# 3. Run the application
./gradlew bootRun
```

The API will start on `http://localhost:8080`

---

## 🔗 Related Repositories

- [CRM UI](https://github.com/Ajay-Kumar-13/crm-ui) — React.js frontend
- [Users API](https://github.com/Ajay-Kumar-13/Users-API) — User management microservice

---

## 👤 Author

**Ajay Kumar Pulletikurthi**  
[LinkedIn](https://www.linkedin.com/in/ajay-kumar-pulletikurthi-677b80222/) · [GitHub](https://github.com/Ajay-Kumar-13)
