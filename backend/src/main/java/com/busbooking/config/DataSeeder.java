package com.busbooking.config;

import com.busbooking.dao.BusRepository;
import com.busbooking.dao.RouteRepository;
import com.busbooking.dao.StopRepository;
import com.busbooking.dao.UserRepository;
import com.busbooking.dao.DriverRepository;
import com.busbooking.dao.AlertRepository;
import com.busbooking.dao.AnomalyLogRepository;
import com.busbooking.model.Bus;
import com.busbooking.model.Route;
import com.busbooking.model.Stop;
import com.busbooking.model.User;
import com.busbooking.model.Driver;
import com.busbooking.model.Alert;
import com.busbooking.model.AnomalyLog;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    @SuppressWarnings("null")
    public CommandLineRunner seedData(UserRepository userRepository, 
                                      BusRepository busRepository, 
                                      RouteRepository routeRepository,
                                      StopRepository stopRepository,
                                      DriverRepository driverRepository,
                                      AlertRepository alertRepository,
                                      AnomalyLogRepository anomalyLogRepository) {
        return args -> {
            // Seed Admin User
            if (userRepository.findAll().stream().noneMatch(u -> "admin".equals(u.getUsername()))) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPasswordHash("admin123");
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            // Seed sample customer
            if (userRepository.findAll().stream().noneMatch(u -> "Aarushm".equals(u.getUsername()))) {
                User customer = new User();
                customer.setUsername("Aarushm");
                customer.setPasswordHash("123456"); // You can use whichever password you were trying to use
                customer.setRole("CUSTOMER");
                userRepository.save(customer);
            }

            // Seed sample stops if empty
            if (stopRepository.count() == 0) {
                Stop s1 = Stop.builder().name("Mumbai Central").lat(18.9690).lng(72.8205).city("Mumbai").state("Maharashtra").isActive(true).build();
                Stop s2 = Stop.builder().name("Pune Swargate").lat(18.5018).lng(73.8636).city("Pune").state("Maharashtra").isActive(true).build();
                Stop s3 = Stop.builder().name("Bangalore Majestic").lat(12.9569).lng(77.5703).city("Bangalore").state("Karnataka").isActive(true).build();
                s1 = stopRepository.save(s1);
                s2 = stopRepository.save(s2);
                s3 = stopRepository.save(s3);

                // Seed sample routes
                Route r1 = Route.builder().name("Mumbai - Pune Express").startStopId(s1.getStopId()).endStopId(s2.getStopId()).distanceKm(150.0).estimatedDurationMinutes(180).isActive(true).routeType("EXPRESS").build();
                Route r2 = Route.builder().name("Pune - Bangalore Sleeper").startStopId(s2.getStopId()).endStopId(s3.getStopId()).distanceKm(840.0).estimatedDurationMinutes(800).isActive(true).routeType("SLEEPER").build();
                r1 = routeRepository.save(r1);
                r2 = routeRepository.save(r2);

                // Seed sample buses
                Bus b1 = Bus.builder().driverName("Ramesh").registrationNumber("MH-01-AB-1234").capacity(40).status("ACTIVE").currentLat(18.9690).currentLng(72.8205).routeId(r1.getRouteId()).build();
                Bus b2 = Bus.builder().driverName("Suresh").registrationNumber("MH-12-CD-5678").capacity(45).status("ACTIVE").currentLat(18.5018).currentLng(73.8636).routeId(r2.getRouteId()).build();
                Bus b3 = Bus.builder().driverName("Mahesh").registrationNumber("KA-01-EF-9012").capacity(30).status("ACTIVE").currentLat(12.9569).currentLng(77.5703).routeId(r1.getRouteId()).build();
                busRepository.save(b1);
                busRepository.save(b2);
                busRepository.save(b3);
            }

            if (driverRepository.count() == 0) {
                driverRepository.save(Driver.builder()
                        .name("Ramesh Kumar")
                        .phone("+91-9876543210")
                        .licenseNumber("DL-1001-A")
                        .busId(1L)
                        .status("Active")
                        .joinDate("2021-05-10")
                        .rating(4.8)
                        .totalTrips(120)
                        .build());
                driverRepository.save(Driver.builder()
                        .name("Suresh Singh")
                        .phone("+91-8765432109")
                        .licenseNumber("DL-1002-B")
                        .busId(2L)
                        .status("Active")
                        .joinDate("2022-01-15")
                        .rating(4.5)
                        .totalTrips(85)
                        .build());
                driverRepository.save(Driver.builder()
                        .name("Rajesh Patel")
                        .phone("+91-7654321098")
                        .licenseNumber("MH-1003-C")
                        .busId(3L)
                        .status("On Leave")
                        .joinDate("2020-11-20")
                        .rating(4.9)
                        .totalTrips(210)
                        .build());
            }

            if (anomalyLogRepository.count() == 0) {
                anomalyLogRepository.save(new AnomalyLog("Multiple Bookings", "User ID 45 made 10 bookings in 5 mins"));
                anomalyLogRepository.save(new AnomalyLog("Payment Failure Spike", "Route 2 recorded 15 failed payment attempts"));
                anomalyLogRepository.save(new AnomalyLog("Unusual Demand", "Route 5 demand spiked by 200% abruptly"));
            }

            if (alertRepository.count() == 0) {
                alertRepository.save(Alert.builder()
                        .busId(1L)
                        .type("Maintenance Alert")
                        .message("Engine oil temperature high")
                        .timestamp(System.currentTimeMillis())
                        .resolved(false)
                        .build());
                alertRepository.save(Alert.builder()
                        .busId(2L)
                        .type("Schedule Delay")
                        .message("Bus running 30 mins behind schedule")
                        .timestamp(System.currentTimeMillis() - 3600000)
                        .resolved(false)
                        .build());
            }
        };
    }
}
