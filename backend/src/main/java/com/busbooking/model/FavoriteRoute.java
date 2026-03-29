package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite_routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;
    
    private Long userId;
    private Long routeId;
    private Long fromStopId;
    private Long toStopId;
    
    private String nickname;
}