-- Bus Booking System - Sample Data

-- Insert Sample Customers
INSERT INTO customers (name, email, phone, password, address, city, state, postal_code) VALUES
('Aarush Sharma', 'aarush@example.com', '+91-9876543210', 'hashed_password_1', '123 Main St', 'Delhi', 'Delhi', '110001'),
('Priya Patel', 'priya@example.com', '+91-9876543211', 'hashed_password_2', '456 Oak Ave', 'Mumbai', 'Maharashtra', '400001'),
('Rajesh Kumar', 'rajesh@example.com', '+91-9876543212', 'hashed_password_3', '789 Pine Rd', 'Bangalore', 'Karnataka', '560001'),
('Anjali Verma', 'anjali@example.com', '+91-9876543213', 'hashed_password_4', '321 Elm St', 'Hyderabad', 'Telangana', '500001'),
('Vikram Singh', 'vikram@example.com', '+91-9876543214', 'hashed_password_5', '654 Maple Dr', 'Pune', 'Maharashtra', '411001');

-- Insert Sample Buses
INSERT INTO buses (bus_name, bus_number, capacity, bus_type, operator_name, ac_status, amenities) VALUES
('Express Deluxe', 'DB-001', 50, 'AC Sleeper', 'Travel King', TRUE, 'WiFi, USB Charging, Blankets'),
('City Comfort', 'DB-002', 45, 'AC Seater', 'City Travels', TRUE, 'Reclining Seats, WiFi, Charging'),
('Highway Star', 'DB-003', 55, 'AC Sleeper', 'Road Master', TRUE, 'WiFi, USB Port, Meal Service'),
('Rapid Runner', 'DB-004', 50, 'AC Seater', 'Express Tours', TRUE, 'WiFi, Reading Light, Recline'),
('Comfort Plus', 'DB-005', 48, 'AC Semi-Sleeper', 'Premium Travel', TRUE, 'WiFi, Charging, Blankets');

-- Insert Sample Routes
INSERT INTO routes (route_name, starting_point, ending_point, distance_km, estimated_duration_minutes) VALUES
('Delhi to Mumbai', 'Delhi', 'Mumbai', 1440, 1320),
('Mumbai to Bangalore', 'Mumbai', 'Bangalore', 1000, 900),
('Delhi to Bangalore', 'Delhi', 'Bangalore', 2150, 1860),
('Pune to Hyderabad', 'Pune', 'Hyderabad', 570, 480),
('Delhi to Jaipur', 'Delhi', 'Jaipur', 240, 240);

-- Insert Sample Schedules
INSERT INTO schedules (bus_id, route_id, departure_time, arrival_time, departure_date, available_seats, price, status) VALUES
(1, 1, '18:00:00', '08:00:00', DATE_ADD(CURDATE(), INTERVAL 1 DAY), 50, 1500.00, 'AVAILABLE'),
(2, 2, '09:00:00', '18:00:00', DATE_ADD(CURDATE(), INTERVAL 1 DAY), 45, 1200.00, 'AVAILABLE'),
(3, 3, '20:00:00', '12:00:00', DATE_ADD(CURDATE(), INTERVAL 2 DAY), 55, 2000.00, 'AVAILABLE'),
(4, 4, '14:00:00', '20:00:00', DATE_ADD(CURDATE(), INTERVAL 1 DAY), 50, 800.00, 'AVAILABLE'),
(5, 5, '06:00:00', '10:00:00', DATE_ADD(CURDATE(), INTERVAL 1 DAY), 48, 500.00, 'AVAILABLE'),
(1, 1, '20:00:00', '10:00:00', DATE_ADD(CURDATE(), INTERVAL 3 DAY), 50, 1500.00, 'AVAILABLE'),
(2, 2, '11:00:00', '20:00:00', DATE_ADD(CURDATE(), INTERVAL 2 DAY), 45, 1200.00, 'AVAILABLE');

-- Insert Sample Bookings
INSERT INTO bookings (customer_id, schedule_id, seat_numbers, number_of_seats, total_price, booking_status, payment_status) VALUES
(1, 1, 'A1,A2', 2, 3000.00, 'CONFIRMED', 'PAID'),
(2, 2, 'B5,B6,B7', 3, 3600.00, 'CONFIRMED', 'PAID'),
(3, 3, 'C10', 1, 2000.00, 'CONFIRMED', 'PENDING'),
(4, 4, 'D3,D4,D5,D6', 4, 3200.00, 'CONFIRMED', 'PAID'),
(5, 5, 'E1,E2', 2, 1000.00, 'PENDING', 'PENDING');

-- Insert Sample Payments
INSERT INTO payments (booking_id, customer_id, amount, payment_method, transaction_id, payment_status) VALUES
(1, 1, 3000.00, 'Credit Card', 'TXN-001-2024', 'SUCCESS'),
(2, 2, 3600.00, 'Debit Card', 'TXN-002-2024', 'SUCCESS'),
(4, 4, 3200.00, 'UPI', 'TXN-004-2024', 'SUCCESS');

-- Insert Sample Reviews
INSERT INTO reviews (booking_id, customer_id, schedule_id, rating, review_text) VALUES
(1, 1, 1, 5, 'Excellent service, comfortable bus, arrived on time.'),
(2, 2, 2, 4, 'Good journey, the bus was clean and comfortable.'),
(4, 4, 4, 5, 'Outstanding experience, will book again!');

-- Insert Sample Seats (for bus DB-001)
INSERT INTO seats (bus_id, seat_number, seat_type, is_available) VALUES
(1, 'A1', 'Window', FALSE),
(1, 'A2', 'Middle', FALSE),
(1, 'A3', 'Aisle', TRUE),
(1, 'A4', 'Window', TRUE),
(1, 'A5', 'Middle', TRUE);

-- Insert Sample Admin
INSERT INTO admins (username, email, password, role) VALUES
('admin', 'admin@busbooking.com', 'hashed_admin_password', 'ADMIN'),
('support', 'support@busbooking.com', 'hashed_support_password', 'SUPPORT');
