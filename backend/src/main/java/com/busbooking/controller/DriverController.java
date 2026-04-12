package com.busbooking.controller;

import com.busbooking.dao.DriverRepository;
import com.busbooking.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drivers")

public class DriverController {

    @Autowired
    private DriverRepository driverRepository;

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @PostMapping
    @SuppressWarnings("null")
    public Driver createDriver(@RequestBody Driver driver) {
        return driverRepository.save(driver);
    }

    @PutMapping("/{id}/status")
    @SuppressWarnings("null")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver != null) {
            driver.setStatus(statusUpdate.get("status"));
            driverRepository.save(driver);
            return ResponseEntity.ok(driver);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/assign-bus/{busId}")
    @SuppressWarnings("null")
    public ResponseEntity<?> assignBus(@PathVariable Long id, @PathVariable Long busId) {
        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver != null) {
            driver.setBusId(busId);
            driverRepository.save(driver);
            return ResponseEntity.ok(driver);
        }
        return ResponseEntity.notFound().build();
    }
}
