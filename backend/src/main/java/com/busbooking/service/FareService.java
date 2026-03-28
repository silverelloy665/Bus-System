package com.busbooking.service;

import com.busbooking.dao.FareRepository;
import com.busbooking.model.Fare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

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
     * @param fromStopId The origin stop ID
     * @param toStopId The destination stop ID
     * @return The calculated fare amount
     */
    public Double calculateFare(Long fromStopId, Long toStopId) {
        // Base fare calculation: 5 + 2 per stop distance
        Long distance = Math.abs(toStopId - fromStopId);
        Double baseFare = 5.0 + (distance * 2.0);
        return Math.round(baseFare * 100.0) / 100.0;
    }

    /**
     * Suggest a fare for a specific route
     * @param routeId The route ID
     * @return Suggested fare for the route
     */
    public Double suggestFare(Long routeId) {
        // Suggested fare: base fare + 10% markup
        Double suggestedFare = (5.0 + routeId * 1.5) * 1.1;
        return Math.round(suggestedFare * 100.0) / 100.0;
    }

    /**
     * Validate a fare change request
     * @param oldFare The previous fare amount
     * @param newFare The new fare amount
     * @return Validation status: "OK", "WARNING: Loss", or "WARNING: Unethical"
     */
    public String validateFareChange(Double oldFare, Double newFare) {
        if (oldFare == null || newFare == null) {
            return "OK";
        }

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
