package com.busbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

@RestController
@RequestMapping("/api/shared-rides")

public class SharedRideController {

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableRides(@RequestParam Long from, @RequestParam Long to) {
        // Mock data for speed
        List<Map<String, Object>> rides = Arrays.asList(
            Map.of("id", 101, "busId", 1, "routeId", 1, "time", "18:30 Today", "seatsLeft", 2, "pricePerSeat", 45, "maxPassengers", 4, "currentPassengers", 2),
            Map.of("id", 102, "busId", 2, "routeId", 1, "time", "20:00 Today", "seatsLeft", 1, "pricePerSeat", 40, "maxPassengers", 4, "currentPassengers", 3)
        );
        return ResponseEntity.ok(rides);
    }
}
