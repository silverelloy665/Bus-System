package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fare Entity - Represents fare pricing rules
 * Stores base fares, multipliers, and price overrides per route
 */
@Entity
@Table(name = "fares")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fare_id")
    private Long fareId;

    @Column(name = "route_id", nullable = false)
    private Long routeId;

@Column(name = "base_fare", nullable = false)
    private Double baseFare;

    @Column(name = "peak_multiplier")
    private Double peakMultiplier; // 1.0 to 2.0 multiplier for peak hours      

    @Column(name = "off_peak_multiplier")
    private Double offPeakMultiplier; // Discount multiplier for off-peak       

    @Column(name = "admin_override")
    private Double adminOverride; // Admin set price override if any

    @Column(name = "per_km_charge")
    private Double perKmCharge; // Additional charge per km

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "last_updated")
    private Long lastUpdated;

    @Column(name = "effective_from")
    private Long effectiveFrom;

    @Column(name = "effective_till")
    private Long effectiveTill;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * Calculate final fare based on multipliers
     */
    public Double calculateFareWithMultiplier(Double multiplier) {
        if (adminOverride != null && adminOverride > 0) {
            return adminOverride;
        }
        if (multiplier != null) {
            return baseFare * multiplier;
        }
        return baseFare;
    }

    /**
     * Lifecycle method to set timestamps on entity creation
     */
    @PrePersist
    protected void onCreate() {
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
        lastUpdated = System.currentTimeMillis();
        if (isActive == null) {
            isActive = true;
        }
        if (peakMultiplier == null) {
            peakMultiplier = 1.5;
        }
        if (offPeakMultiplier == null) {
            offPeakMultiplier = 0.8;
        }
    }

    /**
     * Lifecycle method to update timestamps on entity update
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = System.currentTimeMillis();
        lastUpdated = System.currentTimeMillis();
    }
}
