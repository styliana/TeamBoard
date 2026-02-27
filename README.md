# TeamBoard Coffee ☕

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)

A full-stack web application built for the Advanced Web Application Programming (ZTPAI) course. The platform allows users to organize and join quick coffee meetings.

## 🎥 Demo Video

**[👉 Click here to watch the demo video (2-3 min) 👈](WSTAW_TUTAJ_LINK_DO_YOUTUBE_LUB_DYSKU)**

---

## 📸 Screenshots

| | |
|:---:|:---:|
| <img src="link_do_zdjecia_1.png" width="400"/> <br> *1. Login and Registration* | <img src="link_do_zdjecia_2.png" width="400"/> <br> *2. Main Dashboard & Coffee Ads* |
| <img src="link_do_zdjecia_3.png" width="400"/> <br> *3. Creating a New Ad* | <img src="link_do_zdjecia_4.png" width="400"/> <br> *4. Spring Events Console Logs* |

*(Note: Replace `link_do_zdjecia_X.png` with actual paths to your images, e.g., `docs/login.png`)*

---

## 🚀 Features (Course Requirements Fulfilled)

This project implements all the requirements for the **5.0 grade**:
* **Layered Architecture:** Clear separation of Controller, Service, and Repository layers.
* **Database Integration:** Relational data mapping using Spring Data JPA and PostgreSQL.
* **RESTful API:** Full CRUD operations for managing coffee meetings.
* **DTO & Validation:** Strict data transfer objects with Jakarta Validation (`@NotBlank`, `@Size`).
* **Security:** Implemented Basic Authentication with BCrypt password hashing.
* **Exception Handling:** Global exception handler for clean HTTP error responses (400, 401, etc.).
* **Unit Testing:** Business logic tested using **JUnit 5** and **Mockito**.
* **Event-Driven Architecture:** Uses **Spring Events** and SLF4J for decoupled notification logging when a user joins a meeting.
* **Frontend:** A responsive single-page application built with **Angular** consuming the REST API.

---

## 🛠️ How to Run the Project

### 1. Database (PostgreSQL)
Ensure you have Docker installed. Navigate to the backend directory and start the database:
```bash
docker-compose up -d


<img width="1674" height="884" alt="image" src="https://github.com/user-attachments/assets/1c0f1b04-1b93-4221-82c4-f3b2e8cb285c" />
