package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Route Entity - Represents a bus route between two stops
 * Stores route information and connectivity
 */
@Entity
@Table(name = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "start_stop_id", nullable = false)
    private Long startStopId;

    @Column(name = "end_stop_id", nullable = false)
    private Long endStopId;

    @Column(name = "distance_km")
    private Double distanceKm;

    @Column(name = "estimated_duration_minutes")
    private Integer estimatedDurationMinutes;

    @Column(name = "total_stops")
    private Integer totalStops;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "route_type", length = 50)
    private String routeType; // EXPRESS, LOCAL, INTERCITY, etc.

    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * Lifecycle method to set createdAt timestamp on entity creation
     */
    @PrePersist
    protected void onCreate() {
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
        if (isActive == null) {
            isActive = true;
        }
    }

    /**
     * Lifecycle method to update updatedAt timestamp on entity update
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = System.currentTimeMillis();
    }
}
