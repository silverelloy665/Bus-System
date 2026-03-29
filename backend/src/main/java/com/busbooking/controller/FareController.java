package com.busbooking.controller;

import com.busbooking.service.FareService;
import com.busbooking.dto.SmartFareResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fares")
public class FareController {

    @Autowired
    private FareService fareService;

    @GetMapping("/route/{routeId}/calculate")
    public ResponseEntity<SmartFareResponse> calculateFare(@PathVariable Long routeId, @RequestParam(required = false) Long busId) {
        return ResponseEntity.ok(fareService.calculateFare(routeId, busId));
    }

    @GetMapping("/route/{routeId}/suggest")
    public ResponseEntity<Double> suggestFare(@PathVariable Long routeId) {
        return ResponseEntity.ok(fareService.suggestFare(routeId));
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateFareChange(@RequestParam Double oldFare, @RequestParam Double newFare) {
        return ResponseEntity.ok(fareService.validateFareChange(oldFare, newFare));
    }

    @PostMapping
    public ResponseEntity<com.busbooking.model.Fare> saveFare(@RequestBody com.busbooking.model.Fare fare) {
        return ResponseEntity.ok(fareService.saveFare(fare));
    }
}
