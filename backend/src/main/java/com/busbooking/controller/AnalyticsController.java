package com.busbooking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")

public class AnalyticsController {

    @GetMapping("/kpi-summary")
    public Map<String, Object> getKpiSummary() {
        Map<String, Object> map = new HashMap<>();
        map.put("totalRevenue", 125000);
        map.put("activeBuses", 42);
        map.put("totalBookings", 850);
        map.put("satisfaction", 4.8);
        return map;
    }

    @GetMapping("/daily-revenue")
    public List<Map<String, Object>> getDailyRevenue() {
        return Arrays.asList(
            Map.of("date", "2023-10-01", "revenue", 12000),
            Map.of("date", "2023-10-02", "revenue", 15000),
            Map.of("date", "2023-10-03", "revenue", 14000),
            Map.of("date", "2023-10-04", "revenue", 18000),
            Map.of("date", "2023-10-05", "revenue", 21000)
        );
    }

    @GetMapping("/bookings-per-route")
    public List<Map<String, Object>> getBookingsPerRoute() {
        return Arrays.asList(
            Map.of("route", "Mumbai - Pune", "bookings", 300),
            Map.of("route", "Delhi - Bangalore", "bookings", 150),
            Map.of("route", "Hyderabad - Chennai", "bookings", 200),
            Map.of("route", "Pune - Goa", "bookings", 250)
        );
    }

    @GetMapping("/payment-breakdown")
    public List<Map<String, Object>> getPaymentBreakdown() {
        return Arrays.asList(
            Map.of("method", "Credit Card", "amount", 50000),
            Map.of("method", "UPI", "amount", 45000),
            Map.of("method", "Debit Card", "amount", 20000),
            Map.of("method", "Wallet", "amount", 10000)
        );
    }

    @GetMapping("/hourly-bookings")
    public List<Map<String, Object>> getHourlyBookings() {
        return Arrays.asList(
            Map.of("hour", "08:00", "count", 20),
            Map.of("hour", "10:00", "count", 45),
            Map.of("hour", "12:00", "count", 30),
            Map.of("hour", "14:00", "count", 50),
            Map.of("hour", "16:00", "count", 40),
            Map.of("hour", "18:00", "count", 60),
            Map.of("hour", "20:00", "count", 80)
        );
    }
}
