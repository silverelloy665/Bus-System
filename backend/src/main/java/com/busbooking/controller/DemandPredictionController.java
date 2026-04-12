package com.busbooking.controller;

import com.busbooking.dto.DemandPredictionDTO;
import com.busbooking.service.DemandPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class DemandPredictionController {

    @Autowired
    private DemandPredictionService demandPredictionService;

    @GetMapping("/busy-routes")
    public ResponseEntity<List<DemandPredictionDTO>> getBusyRoutes() {
        return ResponseEntity.ok(demandPredictionService.getPredictionsForNextHours(3));
    }
}
