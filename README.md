# Bus Booking System

A full-stack web application designed for booking bus tickets, estimating fares, and tracking real-time bus locations. It features a responsive customer-facing frontend, an admin dashboard, and a Java Spring Boot backend built on top of an embedded H2 database.

## Architecture & Tech Stack
-   **Frontend:** HTML5, Tailwind CSS, Leaflet.js (for maps), Vanilla JavaScript (Fetch API).
-   **Backend:** Java 25, Spring Boot 3.4.5, Spring Data JPA, Lombok.
-   **Database:** H2 In-Memory Database (auto-initialized).
-   **Build Tool:** Maven.

## Features Implemented
1.  **User Authentication:** Complete Registration/Login flow (/api/users/register, /api/users/login).
2.  **Interactive Maps:** Renders geographical tracking maps with custom markers utilizing Leaflet.js (map.js).
3.  **Customer Dashboard:** Booking interface supporting active bus fetching and calculated fare check.
4.  **Admin Dashboard:** Interfaces designed for updating route pricing (e.g., dynamically adjusting peak multipliers and enforcing limits).
5.  **Data Seeding:** Backend seeds the internal database with Mumbai localities automatically upon startup so that route/bus data naturally loads on UI without tedious manual inputs.
6.  **CORS Handling:** Robust internal CORS configuration permitting API access from frontend origin (\127.0.0.1:5500\).

## Setup and Running the Project

### Prerequisite Requirements
-   Java JDK 25 installed
-   Maven installed and tracked in PATH \(mvn)\
-   VS Code 'Live Server' extension (for serving statically from \5500\)

### 1. Run the Backend API 
1. Open a terminal and navigate to your ackend/ folder:
    `ash
    cd backend
    `
2. Execute the Spring Boot Server:
    `ash
    mvn spring-boot:run
    `
3. The server will start gracefully on \http://localhost:8080\, logging that H2 Dialect is injected and mock data (Stops, Buses, Routes, Fares) has populated.

### 2. Run the Frontend App
1. Right-click \rontend/index.html\ inside Visual Studio Code.
2. Select **"Open with Live Server"**.
3. It will host the frontend application over \http://127.0.0.1:5500/frontend/\.

---

## API Endpoints Reference 

### User Endpoints 
-   **POST** \/api/users/register\ — Register a new user (Requires: username, phone, passwordHash)
-   **POST** \/api/users/login\ — User authentication (Requires: username, passwordHash)
-   **GET** \/api/users\ — Fetch all registered users
-   **PUT** \/api/users/{id}/location?lat={X}&lng={Y}\ — Update logged-in user geographical coordinates

### Booking Endpoints 
-   **POST** \/api/bookings\ — Create a new ticket booking 
-   **GET** \/api/bookings/user/{userId}\ — Get past/present bookings for specific user

### Bus & Map Endpoints 
-   **GET** \/api/buses\ — Retrieves a list of active buses internally stored alongside their lat/lng variables.
-   **PUT** \/api/buses/{id}/location?lat={X}&lng={Y}\ — Post mock location pings to trace live-bus.

### Admin Fare Overrides
-   **GET** \/api/fares/route/{routeId}/calculate\ — Estimate real-time dynamic pricing per route
-   **PUT** \/api/fares/{id}/peak\ — Update peak/pricing multiplier matrices (Requires: \multiplier\ arg)
