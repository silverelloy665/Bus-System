package com.busbooking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long routeId;
    private Long userId;
    private int rating;
    private String title;
    private String body;
    @Builder.Default
    private int helpfulCount = 0;
    private long timestamp;
}
