# Bus Booking Web Application

A full-stack real-time bus booking system with customer and admin portals.

## Project Structure

```
Bus System/
├── backend/                 # Spring Boot REST API
│   ├── src/main/java/com/busbooking/
│   │   ├── BusBookingApplication.java
│   │   ├── controller/      # REST API endpoints
│   │   ├── service/         # Business logic
│   │   ├── model/           # Entity classes
│   │   ├── dao/             # Data access layer
│   │   └── util/            # Utility classes
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml              # Maven configuration
│
├── frontend/
│   ├── customer/            # Customer web interface
│   │   ├── index.html
│   │   ├── css/
│   │   ├── js/
│   │   └── assets/
│   └── admin/               # Admin dashboard
│       ├── index.html
│       ├── css/
│       ├── js/
│       └── assets/
│
├── database/
│   └── bus_booking_schema.sql
│
└── README.md
```

## Tech Stack

- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Backend**: Java 17, Spring Boot 3.1.0, Maven
- **Database**: MySQL 8.0
- **Maps**: Leaflet.js
- **API**: RESTful JSON APIs
- **Real-time**: Polling mechanism (future: WebSockets)

## Prerequisites

- Java 17+
- Maven 3.8+
- MySQL Server 8.0+
- Modern Web Browser (Chrome, Firefox, Edge)

## Setup Instructions

### 1. Database Setup

```bash
# Start MySQL server
mysql -u root -p

# Run schema file
SOURCE database/bus_booking_schema.sql;
```

### 2. Update Database Configuration

Edit `backend/src/main/resources/application.properties`:
```properties
spring.datasource.username=<your-mysql-user>
spring.datasource.password=<your-mysql-password>
```

### 3. Build and Run Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

API will be available at: `http://localhost:8080/api`

### 4. Run Frontend

For development, you can:
- Option A: Open HTML files directly in browser
- Option B: Use Live Server extension in VS Code
- Option C: Deploy via Spring Boot static resources

Frontend URLs:
- Customer: `http://localhost:3000`
- Admin: `http://localhost:3000/admin`

## Features Roadmap

### Phase 1: Customer Features
- [ ] User Registration & Login
- [ ] Real-time Location Update
- [ ] Bus Tracking on Map
- [ ] Bus Booking
- [ ] Payment Processing

### Phase 2: Admin Features
- [ ] Admin Login
- [ ] Stop Management
- [ ] Route Management (Manual & AI-Assisted)
- [ ] Fare Management & Warnings
- [ ] Dashboard & Analytics

## Git Commit Messages Template

After each feature completion:
```bash
git add .
git commit -m "MILESTONE: Feature Name - Brief Description"
```

## Default Credentials (Testing)

- **Admin**: username: `admin`, password: `admin123`
- Sample buses and routes are pre-populated in schema

## API Port
- Backend: `http://localhost:8080/api`

## Database Port
- MySQL: `localhost:3306`

---

**Version**: 1.0.0  
**Status**: In Development
