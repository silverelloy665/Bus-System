package com.busbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemandPredictionDTO {
    private Long routeId;
    private Integer predictedBookings;
    private String status; // VERY_BUSY, MODERATE, QUIET
    private Double recommendedFareMultiplier;
}
