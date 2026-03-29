package com.busbooking.controller;

import com.busbooking.model.Bus;
import com.busbooking.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")

public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private BusLocationWebSocket busLocationWebSocket;

    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<Bus>> getBusesByRoute(@PathVariable Long routeId) {
        return ResponseEntity.ok(busService.getBusesByRoute(routeId));
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<Bus> updateBusLocation(@PathVariable Long id, @RequestParam Double lat, @RequestParam Double lng) {
        try {
            Bus updatedBus = busService.updateBusLocation(id, lat, lng);
            busLocationWebSocket.broadcastBusLocation(updatedBus);
            return ResponseEntity.ok(updatedBus);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<Bus>> getNearbyBuses(@RequestParam Double lat, @RequestParam Double lng) {
        return ResponseEntity.ok(busService.getNearbyBuses(lat, lng));
    }
}
