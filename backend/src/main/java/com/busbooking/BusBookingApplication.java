package com.busbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.busbooking.dao.StopRepository;
import com.busbooking.dao.RouteRepository;
import com.busbooking.dao.BusRepository;
import com.busbooking.dao.FareRepository;
import com.busbooking.model.Stop;
import com.busbooking.model.Route;
import com.busbooking.model.Bus;
import com.busbooking.model.Fare;

@SpringBootApplication
@EnableScheduling
public class BusBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusBookingApplication.class, args);
        System.out.println("Bus Booking System API started");
    }

    @Bean
    @SuppressWarnings("null")
    public CommandLineRunner initData(
            StopRepository stopRepository,
            RouteRepository routeRepository,
            BusRepository busRepository,
            FareRepository fareRepository) {
        return args -> {
            // Add 3 sample stops near Mumbai if not exists
            if (stopRepository.count() == 0) {
                Stop stop1 = Stop.builder().name("Andheri East").lat(19.1136).lng(72.8697)
                        .city("Mumbai").state("Maharashtra").isActive(true).build();
                Stop stop2 = Stop.builder().name("Bandra West").lat(19.0596).lng(72.8295)
                        .city("Mumbai").state("Maharashtra").isActive(true).build();
                Stop stop3 = Stop.builder().name("Dadar").lat(19.0178).lng(72.8478)
                        .city("Mumbai").state("Maharashtra").isActive(true).build();
                stopRepository.save(stop1);
                stopRepository.save(stop2);
                stopRepository.save(stop3);

                // Add a route
                Route route1 = Route.builder().name("Andheri to Dadar")
                        .startStopId(stop1.getStopId()).endStopId(stop3.getStopId())
                        .distanceKm(12.5).estimatedDurationMinutes(45).isActive(true)
                        .routeType("EXPRESS").build();
                routeRepository.save(route1);

                // Add 1 sample bus near Mumbai
                Bus bus1 = Bus.builder().driverName("Ramesh Kumar")
                        .currentLat(19.0760).currentLng(72.8777)
                        .routeId(route1.getRouteId()).status("ACTIVE")
                        .capacity(40).registrationNumber("MH02-AB-1234").build();
                busRepository.save(bus1);

                // Add 1 sample fare
                Fare fare1 = Fare.builder().routeId(route1.getRouteId())
                        .baseFare(20.0).peakMultiplier(1.5).offPeakMultiplier(0.9)
                        .perKmCharge(1.5).isActive(true).build();
                fareRepository.save(fare1);

                System.out.println("Added initial sample data (Stops, Route, Bus, Fare)");
            }
        };
    }
}
