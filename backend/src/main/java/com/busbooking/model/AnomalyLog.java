package com.busbooking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnomalyLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String description;
    private LocalDateTime detectedAt;
    private boolean resolved;

    public AnomalyLog() {}

    public AnomalyLog(String type, String description) {
        this.type = type;
        this.description = description;
        this.detectedAt = LocalDateTime.now();
        this.resolved = false;
    }
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getDetectedAt() { return detectedAt; }
    public void setDetectedAt(LocalDateTime detectedAt) { this.detectedAt = detectedAt; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
}