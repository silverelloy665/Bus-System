-- Bus Booking System Database Schema (Fully Normalized - 1NF, 2NF, 3NF applied)

-- 1. Customers Table (Base Entity)
CREATE TABLE IF NOT EXISTS customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Buses Table (Base Entity)
CREATE TABLE IF NOT EXISTS buses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bus_number VARCHAR(20) UNIQUE NOT NULL,
    capacity INT NOT NULL,
    bus_type VARCHAR(50),
    operator_name VARCHAR(100),
    ac_status BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Amenities Table (Base Entity to resolve 1NF violation of comma-separated string)
CREATE TABLE IF NOT EXISTS amenities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- 4. Bus Amenities Mapping (Junction pattern for Many-to-Many - 1NF/2NF)
CREATE TABLE IF NOT EXISTS bus_amenities (
    bus_id INT NOT NULL,
    amenity_id INT NOT NULL,
    PRIMARY KEY (bus_id, amenity_id),
    FOREIGN KEY (bus_id) REFERENCES buses(id) ON DELETE CASCADE,
    FOREIGN KEY (amenity_id) REFERENCES amenities(id) ON DELETE CASCADE
);

-- 5. Stops Table (Extracted to resolve Transitive Dependency in Routes - 3NF)
CREATE TABLE IF NOT EXISTS stops (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8)
);

-- 6. Routes Table (Simplified for 3NF - detailed stops are mapped below)
CREATE TABLE IF NOT EXISTS routes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    route_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 7. Route Stops (Junction mapping a continuous path without repeating route logic)
CREATE TABLE IF NOT EXISTS route_stops (
    route_id INT NOT NULL,
    stop_id INT NOT NULL,
    stop_sequence INT NOT NULL,
    distance_from_start_km DECIMAL(6, 2) DEFAULT 0.00,
    PRIMARY KEY (route_id, stop_id),
    FOREIGN KEY (route_id) REFERENCES routes(id) ON DELETE CASCADE,
    FOREIGN KEY (stop_id) REFERENCES stops(id) ON DELETE CASCADE
);

-- 8. Schedules Table (Associates Buses with Routes over Time)
CREATE TABLE IF NOT EXISTS schedules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bus_id INT NOT NULL,
    route_id INT NOT NULL,
    departure_date DATE NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'AVAILABLE',
    UNIQUE KEY unique_schedule (bus_id, route_id, departure_date, departure_time),
    FOREIGN KEY (bus_id) REFERENCES buses(id),
    FOREIGN KEY (route_id) REFERENCES routes(id)
);

-- 9. Bookings Table (Transaction Entity)
CREATE TABLE IF NOT EXISTS bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    schedule_id INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    booking_status VARCHAR(20) DEFAULT 'CONFIRMED',
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (schedule_id) REFERENCES schedules(id)
);

-- 10. Booking Seats (Resolves 1NF violation of array in 'seat_numbers' column)
CREATE TABLE IF NOT EXISTS booking_seats (
    booking_id INT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    PRIMARY KEY (booking_id, seat_number),
    FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE
);

-- 11. Bus Live Locations Table (For Live Map Tracking isolation)
CREATE TABLE IF NOT EXISTS bus_live_locations (
    bus_id INT PRIMARY KEY,
    current_lat DECIMAL(10, 8) NOT NULL,
    current_lng DECIMAL(11, 8) NOT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (bus_id) REFERENCES buses(id) ON DELETE CASCADE
);
