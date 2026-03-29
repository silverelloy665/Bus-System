package com.busbooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loyalty_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoyaltyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "points", nullable = false)
    private Integer points; // positive for earn, negative for redeem

    @Column(name = "description")
    private String description;

    @Column(name = "timestamp")
    private Long timestamp;

    @PrePersist
    protected void onCreate() {
        timestamp = System.currentTimeMillis();
    }
}
