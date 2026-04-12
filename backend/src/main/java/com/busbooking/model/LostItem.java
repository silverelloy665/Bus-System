package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lost_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LostItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    
    private Long busId;
    private Long routeId;
    
    @Column(length = 255)
    private String description;
    
    private String foundDate;
    private String status; // REPORTED, FOUND, CLAIMED
    
    private Long reportedBy; // User ID
    private String contactPhone;
}
