package com.busbooking.service;

import com.busbooking.dao.RouteDemandRepository;
import com.busbooking.dao.RouteRepository;
import com.busbooking.model.RouteDemand;
import com.busbooking.model.Route;
import com.busbooking.dto.DemandPredictionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("null")
public class DemandPredictionService {

    @Autowired
    private RouteDemandRepository routeDemandRepository;

    @Autowired
    private RouteRepository routeRepository;

    // Run every hour to update simulated counts
    @Scheduled(cron = "0 0 * * * *")
    public void updateHourlyDemandCounts() {
        LocalDateTime now = LocalDateTime.now();
        int dayOfWeek = now.getDayOfWeek().getValue();
        int hour = now.getHour();

        List<Route> allRoutes = routeRepository.findAll();
        for (Route route : allRoutes) {
            Optional<RouteDemand> existingOpt = routeDemandRepository.findByRouteIdAndDayOfWeekAndHourOfDay(route.getRouteId(), dayOfWeek, hour);
            
            RouteDemand demand;
            if (existingOpt.isPresent()) {
                demand = existingOpt.get();
                demand.setBookingCount(demand.getBookingCount() + (int)(Math.random() * 5)); // Simulate incremental bookings
            } else {
                demand = RouteDemand.builder()
                        .routeId(route.getRouteId())
                        .dayOfWeek(dayOfWeek)
                        .hourOfDay(hour)
                        .bookingCount((int)(Math.random() * 20)) // Base random seed
                        .build();
            }
            routeDemandRepository.save(demand);
        }
        System.out.println("Hourly demand counts updated for day " + dayOfWeek + " hour " + hour);
    }

    public List<DemandPredictionDTO> getPredictionsForNextHours(int hoursAhead) {
        LocalDateTime now = LocalDateTime.now();
        List<DemandPredictionDTO> predictions = new ArrayList<>();
        
        List<Route> allRoutes = routeRepository.findAll();

        for (Route route : allRoutes) {
            int predictedTotal = 0;
            
            for (int i = 1; i <= hoursAhead; i++) {
                LocalDateTime targetTime = now.plusHours(i);
                int dayOfWeek = targetTime.getDayOfWeek().getValue();
                int hour = targetTime.getHour();
                
                Optional<RouteDemand> historical = routeDemandRepository.findByRouteIdAndDayOfWeekAndHourOfDay(route.getRouteId(), dayOfWeek, hour);
                if (historical.isPresent()) {
                    predictedTotal += historical.get().getBookingCount();
                } else {
                    predictedTotal += (int)(Math.random() * 10); // fallback mock data
                }
            }
            
            double averagePerHour = (double) predictedTotal / hoursAhead;
            String status = "QUIET";
            double recFare = 1.0;
            
            if (averagePerHour > 15) {
                status = "VERY_BUSY";
                recFare = 1.3;
            } else if (averagePerHour > 5) {
                status = "MODERATE";
                recFare = 1.1;
            } else {
                status = "QUIET";
                recFare = 0.9;
            }
            
            predictions.add(new DemandPredictionDTO(route.getRouteId(), predictedTotal, status, recFare));
        }

        // Sort by busiest
        return predictions.stream()
                .sorted((a, b) -> b.getPredictedBookings().compareTo(a.getPredictedBookings()))
                .limit(5)
                .collect(Collectors.toList());
    }
}