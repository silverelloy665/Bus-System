package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "route_demand")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDemand {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_id", nullable = false)
    private Long routeId;

    @Column(name = "day_of_week", nullable = false)
    private Integer dayOfWeek; // 1 (Monday) to 7 (Sunday)

    @Column(name = "hour_of_day", nullable = false)
    private Integer hourOfDay; // 0 to 23

    @Column(name = "booking_count", nullable = false)
    private Integer bookingCount;
}