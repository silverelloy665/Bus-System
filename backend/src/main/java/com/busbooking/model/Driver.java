package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;
    private String name;
    private String phone;
    private String licenseNumber;
    private Long busId;
    private String status;
    private String joinDate;
    private Double rating;
    private Integer totalTrips;
}
