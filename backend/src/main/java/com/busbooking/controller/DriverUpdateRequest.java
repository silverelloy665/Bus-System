package com.busbooking.controller;

import lombok.Data;

@Data
public class DriverUpdateRequest {
    private String driverCode;
    private Double lat;
    private Double lng;
    private Integer passengerCount;
    private String status;
}
