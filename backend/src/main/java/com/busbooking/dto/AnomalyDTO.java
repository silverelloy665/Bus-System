package com.busbooking.dto;

public class AnomalyDTO {
    private Long anomalyId;
    private String type;
    private String description;
    private String detectedAt;

    public AnomalyDTO(Long anomalyId, String type, String description, String detectedAt) {
        this.anomalyId = anomalyId;
        this.type = type;
        this.description = description;
        this.detectedAt = detectedAt;
    }

    public Long getAnomalyId() { return anomalyId; }
    public void setAnomalyId(Long anomalyId) { this.anomalyId = anomalyId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDetectedAt() { return detectedAt; }
    public void setDetectedAt(String detectedAt) { this.detectedAt = detectedAt; }
}