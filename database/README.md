# Bus Booking System - Database Files

This directory contains the database schema and sample data for the Bus Booking System application.

## Files

### 1. `schema.sql`
Contains the complete database schema with all tables:
- **customers**: Stores customer information
- **buses**: Bus details (capacity, type, amenities)
- **routes**: Available routes between cities
- **schedules**: Bus schedules with departure/arrival times and pricing
- **bookings**: Customer bookings
- **payments**: Payment transaction records
- **reviews**: Customer reviews and ratings
- **seats**: Individual seat tracking for buses
- **admins**: Administrator accounts

### 2. `seed_data.sql`
Contains sample data for testing and development:
- 5 sample customers
- 5 sample buses
- 5 sample routes
- 7 sample schedules
- 5 sample bookings
- 3 sample payments
- 3 sample reviews
- 5 sample seats
- 2 sample admin accounts

## Setup Instructions

### 1. Create Database
```bash
mysql -u root -p
CREATE DATABASE bus_booking_system;
USE bus_booking_system;
```

### 2. Load Schema
```bash
mysql -u root -p bus_booking_system < schema.sql
```

### 3. Load Sample Data (Optional)
```bash
mysql -u root -p bus_booking_system < seed_data.sql
```

## Database Connection

Update your `application.properties` with the following configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bus_booking_system
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Schema Overview

### Entity Relationships
```
customers (1) ──── (N) bookings ──── (1) schedules ──── (1) buses
                                           │
                                           └──(1) routes

customers (1) ──── (N) payments ──── (1) bookings

customers (1) ──── (N) reviews ──── (1) bookings

buses (1) ──── (N) seats

buses (1) ──── (N) schedules ──── (1) routes
```

### Key Features
- **Auto-increment IDs**: All tables use auto-increment primary keys
- **Timestamps**: Tracks creation and update times
- **Indexes**: Optimized queries with indexes on frequently searched columns
- **Foreign Keys**: Maintains referential integrity
- **Constraints**: Ensures data validity (e.g., rating between 1-5)
- **Status Tracking**: Bookings and payments have status fields

## Testing

After loading the schema and seed data, you can test with queries like:

```sql
-- View all buses
SELECT * FROM buses;

-- View available schedules
SELECT s.*, b.bus_name, r.route_name 
FROM schedules s
JOIN buses b ON s.bus_id = b.id
JOIN routes r ON s.route_id = r.id
WHERE s.status = 'AVAILABLE';

-- View customer bookings
SELECT c.name, b.*, s.departure_date
FROM bookings b
JOIN customers c ON b.customer_id = c.id
JOIN schedules s ON b.schedule_id = s.id;
```

## Notes
- Replace password hashes with actual encoded passwords in production
- Update sample data as needed for your testing
- Ensure MySQL version 5.7+ for full compatibility
