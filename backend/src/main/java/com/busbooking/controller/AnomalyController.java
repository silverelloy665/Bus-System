package com.busbooking.controller;

import com.busbooking.dao.AnomalyLogRepository;
import com.busbooking.model.AnomalyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
@CrossOrigin(origins = "*")
public class AnomalyController {

    @Autowired
    private AnomalyLogRepository anomalyLogRepository;

    @GetMapping
    public List<AnomalyLog> getAnomalies() {
        return anomalyLogRepository.findAll();
    }
}
