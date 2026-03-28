package com.busbooking.service;

import com.busbooking.dao.BusRepository;
import com.busbooking.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for Bus business logic
 * Handles bus operations, location tracking, and route management
 */
@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    /**
     * Get all buses in the system
     * @return List of all buses
     */
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    /**
     * Get buses operating on a specific route
     * @param routeId The route ID
     * @return List of buses on the specified route
     */
    public List<Bus> getBusesByRoute(Long routeId) {
        return busRepository.findAll().stream()
                .filter(bus -> bus.getRouteId() != null && bus.getRouteId().equals(routeId))
                .collect(Collectors.toList());
    }

    /**
     * Update bus's current location
     * @param busId The bus ID
     * @param lat The latitude
     * @param lng The longitude
     * @return The updated bus object
     */
    public Bus updateBusLocation(Long busId, Double lat, Double lng) {
        Optional<Bus> bus = busRepository.findById(busId);
        if (bus.isPresent()) {
            Bus b = bus.get();
            b.setCurrentLat(lat);
            b.setCurrentLng(lng);
            return busRepository.save(b);
        }
        return null;
    }

    /**
     * Find nearby buses based on coordinates
     * @param lat User's latitude
     * @param lng User's longitude
     * @return List of nearby buses (within reasonable distance)
     */
    public List<Bus> getNearbyBuses(Double lat, Double lng) {
        double radiusInDegrees = 0.05; // Approximately 5.5 km
        
        return busRepository.findAll().stream()
                .filter(bus -> bus.getCurrentLat() != null && bus.getCurrentLng() != null)
                .filter(bus -> {
                    double distance = calculateDistance(lat, lng, 
                            bus.getCurrentLat(), bus.getCurrentLng());
                    return distance <= radiusInDegrees;
                })
                .collect(Collectors.toList());
    }

    /**
     * Calculate distance between two coordinates using Haversine formula approximate
     */
    private double calculateDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lng2 - lng1, 2));
    }
}
