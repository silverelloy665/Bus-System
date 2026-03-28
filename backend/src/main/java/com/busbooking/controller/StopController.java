package com.busbooking.controller;

import com.busbooking.model.Stop;
import com.busbooking.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops")
public class StopController {

    @Autowired
    private StopService stopService;

    @GetMapping
    public ResponseEntity<List<Stop>> getAllStops() {
        return ResponseEntity.ok(stopService.getAllStops());
    }

    @PostMapping
    public ResponseEntity<Stop> addStop(@RequestBody Stop stop) {
        return ResponseEntity.ok(stopService.addStop(stop));
    }
}
