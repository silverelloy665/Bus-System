-- DML Commands (Data Manipulation Language) to Populate Demo Data

-- Insert Customers
INSERT INTO customers (first_name, last_name, email, phone, password_hash) VALUES 
('Rahul', 'Sharma', 'ramesh@example.com', '9876543210', 'hashed_pass_123'),
('Priya', 'Singh', 'priya@example.com', '9876543211', 'hashed_pass_456');

-- Insert Buses
INSERT INTO buses (bus_number, capacity, bus_type, operator_name, ac_status) VALUES 
('MH-01-AB-1234', 40, 'SLEEPER', 'VRL Travels', TRUE),
('KA-02-XY-9876', 50, 'SEATER', 'KSRTC', FALSE);

-- Insert Amenities (Applying 1NF to avoid comma-separated values)
INSERT INTO amenities (name) VALUES ('WiFi'), ('Water Bottle'), ('Blanket'), ('Charging Point');

-- Map Amenities to Buses
INSERT INTO bus_amenities (bus_id, amenity_id) VALUES 
(1, 1), (1, 2), (1, 3), (1, 4), -- Bus 1 has everything
(2, 2); -- Bus 2 only has Water

-- Insert Stops (Normalizing physical locations for 3NF)
INSERT INTO stops (name, city, state, latitude, longitude) VALUES 
('Andheri East', 'Mumbai', 'Maharashtra', 19.1136, 72.8697),
('Bandra West', 'Mumbai', 'Maharashtra', 19.0596, 72.8295),
('Dadar', 'Mumbai', 'Maharashtra', 19.0178, 72.8478),
('Pune Majestic', 'Pune', 'Maharashtra', 18.5204, 73.8567);

-- Insert Routes
INSERT INTO routes (route_name) VALUES 
('Mumbai Local Express'),
('Mumbai to Pune Night Rider');

-- Map Stops to Routes (Route sequence details)
INSERT INTO route_stops (route_id, stop_id, stop_sequence, distance_from_start_km) VALUES 
(1, 1, 1, 0.0),      -- Starts at Andheri
(1, 2, 2, 8.5),      -- Goes to Bandra
(1, 3, 3, 14.2),     -- Ends at Dadar
(2, 3, 1, 0.0),      -- Starts at Dadar
(2, 4, 2, 150.0);    -- Ends at Pune

-- Insert Schedules
INSERT INTO schedules (bus_id, route_id, departure_date, departure_time, arrival_time, price, status) VALUES 
(1, 1, '2026-04-12', '08:00:00', '09:30:00', 50.00, 'AVAILABLE'),
(2, 2, '2026-04-12', '22:00:00', '02:00:00', 450.00, 'AVAILABLE');

-- Insert Bookings (Transaction Records)
INSERT INTO bookings (customer_id, schedule_id, total_price, booking_status) VALUES 
(1, 1, 100.00, 'CONFIRMED'),
(2, 2, 450.00, 'CONFIRMED');

-- Map Booking Seats (Resolving 1NF violation of 'seat_numbers VARCHAR(255)' with individual rows)
INSERT INTO booking_seats (booking_id, seat_number) VALUES 
(1, 'A1'), (1, 'A2'),  -- Customer 1 booked 2 seats
(2, 'U4');             -- Customer 2 booked 1 seat

-- Insert Bus Live Locations (Simulating the active map points)
INSERT INTO bus_live_locations (bus_id, current_lat, current_lng) VALUES 
(1, 19.0760, 72.8777),  -- Somewhere between Andheri and Bandra
(2, 18.9690, 72.8205);
