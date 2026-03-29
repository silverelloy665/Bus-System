package com.busbooking.dto;

import java.util.Map;

public class AnalyticsDTO {
    private Map<String, Object> data;
    public AnalyticsDTO(Map<String, Object> data) { this.data = data; }
    public Map<String, Object> getData() { return data; }
}