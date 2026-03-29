package com.busbooking.controller;

import com.busbooking.dao.RatingRepository;
import com.busbooking.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "*")
@SuppressWarnings("null")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @PostMapping
    public ResponseEntity<Rating> submitRating(@RequestBody Rating rating) {
        rating.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.ok(ratingRepository.save(rating));
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<?> getBusRating(@PathVariable Long busId) {
        List<Rating> ratings = ratingRepository.findByBusId(busId);
        if (ratings.isEmpty()) return ResponseEntity.ok(Map.of("average", 0, "count", 0));
        
        double avg = ratings.stream().mapToInt(Rating::getBusRating).average().orElse(0.0);
        return ResponseEntity.ok(Map.of("average", Math.round(avg * 10) / 10.0, "count", ratings.size()));
    }
}