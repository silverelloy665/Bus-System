package com.busbooking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "maintenance_schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private Long busId;
    private String type;
    private String scheduledDate;
    private String completedDate;
    private String notes;
    private String status;
    private Double cost;
}
