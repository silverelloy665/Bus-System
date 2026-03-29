package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Booking Entity - Represents a bus booking/ticket
 * Stores booking details, route information, and payment status
 */
@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "bus_id", nullable = false)
    private Long busId;

    @Column(name = "from_stop_id", nullable = false)
    private Long fromStopId;

    @Column(name = "to_stop_id", nullable = false)
    private Long toStopId;

    @Column(name = "fare", nullable = false)
    private Double fare;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod; // WALLET, UPI, CREDIT_CARD, CASH, etc.

    @Column(name = "status", nullable = false, length = 50)
    private String status; // PENDING, CONFIRMED, COMPLETED, CANCELLED, etc.

    @Column(name = "booked_at", nullable = false)
    private Long bookedAt;

    @Column(name = "journey_date")
    private Long journeyDate;

    @Column(name = "departure_time")
    private Long departureTime;

    @Column(name = "seat_number", length = 50)
    private String seatNumber;

    @Column(name = "seat_class", length = 50)
    private String seatClass;

    @Column(name = "passenger_count")
    private Integer passengerCount;

    @Column(name = "special_requirements", length = 255)
    private String specialRequirements;

    @Column(name = "cancellation_reason", length = 255)
    private String cancellationReason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * Lifecycle method to set createdAt and bookedAt timestamps
     */
    @PrePersist
    protected void onCreate() {
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
        if (bookedAt == null) {
            bookedAt = System.currentTimeMillis();
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
