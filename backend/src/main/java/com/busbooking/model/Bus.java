package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bus Entity - Represents a bus in the system
 * Stores bus information, location, and operational status
 */
@Entity
@Table(name = "buses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Long busId;

    @Column(name = "driver_name", nullable = false, length = 100)
    private String driverName;

    @Column(name = "current_lat")
    private Double currentLat;

    @Column(name = "current_lng")
    private Double currentLng;

    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "status", nullable = false, length = 50)
    private String status; // ACTIVE, INACTIVE, MAINTENANCE, etc.

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "registration_number", unique = true, length = 20)
    private String registrationNumber;

    @Column(name = "last_location_update")
    private Long lastLocationUpdate;

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
    }

    /**
     * Lifecycle method to update updatedAt timestamp on entity update
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = System.currentTimeMillis();
    }
}
