package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    
    private Long bookingId;
    private Long userId;
    private Long busId;
    private Long driverId;
    
    private Integer busRating;
    private Integer driverRating;
    private Integer punctualityRating;
    
    @Column(length = 500)
    private String comment;
    
    private Long timestamp;
}