package com.busbooking.service;

import com.busbooking.dao.FareRepository;
import com.busbooking.model.Fare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for Fare business logic
 * Handles fare calculation, suggestion, and validation
 */
@Service
public class FareService {

    @Autowired
    private FareRepository fareRepository;

    /**
     * Calculate fare between two stops
     * @param fromStopId The origin stop ID (informational)
     * @param toStopId The destination stop ID (informational)
     * @return The calculated fare amount
     */
    public Double calculateFare(@NonNull Long fromStopId, @NonNull Long toStopId) {
        // Calculate base fare: 5 + distance based calculation
        Long distance = Math.abs(toStopId - fromStopId);
        Double baseFare = 5.0 + (distance * 2.0);
        return Math.round(baseFare * 100.0) / 100.0;
    }

    /**
     * Suggest a fare for a specific route
     * @param routeId The route ID
     * @return Suggested fare for the route
     */
    public Double suggestFare(@NonNull Long routeId) {
        // Fetch fares for this route from database
        List<Fare> routeFares = fareRepository.findByRouteId(routeId);
        
        // Calculate suggestion based on existing fares
        if (!routeFares.isEmpty()) {
            Fare routeFare = routeFares.get(0);
            
            // Use admin override if set
            if (routeFare.getAdminOverride() != null && routeFare.getAdminOverride() > 0) {
                return routeFare.getAdminOverride();
            }
            
            // Use base fare with peak multiplier
            Double baseFare = routeFare.getBaseFare() != null ? routeFare.getBaseFare() : 5.0;
            Double peakMultiplier = routeFare.getPeakMultiplier() != null ? routeFare.getPeakMultiplier() : 1.5;
            Double suggestedFare = baseFare * peakMultiplier;
            return Math.round(suggestedFare * 100.0) / 100.0;
        }
        
        // Default suggestion if no fares found for route
        Double suggestedFare = (5.0 + routeId * 0.5) * 1.5;
        return Math.round(suggestedFare * 100.0) / 100.0;
    }

    /**
     * Validate a fare change request
     * @param oldFare The previous fare amount
     * @param newFare The new fare amount
     * @return Validation status: "OK", "WARNING: Loss", or "WARNING: Unethical"
     */
    public String validateFareChange(@NonNull Double oldFare, @NonNull Double newFare) {
        double percentChange = ((newFare - oldFare) / oldFare) * 100;

        // Loss warning if fare decreased by more than 20%
        if (percentChange < -20.0) {
            return "WARNING: Loss";
        }

        // Unethical warning if fare increased by more than 50%
        if (percentChange > 50.0) {
            return "WARNING: Unethical";
        }

        return "OK";
    }
}
