package com.busbooking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    private String name;
    private String state;
    private String country;
    private Double centerLat;
    private Double centerLng;
    private Boolean isActive;
}
