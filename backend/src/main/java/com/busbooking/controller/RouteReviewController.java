package com.busbooking.controller;

import com.busbooking.dao.RouteReviewRepository;
import com.busbooking.model.RouteReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class RouteReviewController {

    @Autowired
    private RouteReviewRepository repository;

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<RouteReview>> getReviews(
            @PathVariable Long routeId, 
            @RequestParam(defaultValue = "recent") String sort) {
        if ("helpful".equalsIgnoreCase(sort)) {
            return ResponseEntity.ok(repository.findByRouteIdOrderByHelpfulCountDesc(routeId));
        }
        return ResponseEntity.ok(repository.findByRouteIdOrderByTimestampDesc(routeId));
    }

    @PostMapping
    public ResponseEntity<RouteReview> addReview(@RequestBody RouteReview review) {
        review.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.ok(repository.save(review));
    }

    @PutMapping("/{id}/helpful")
    public ResponseEntity<RouteReview> markHelpful(@PathVariable Long id) {
        return repository.findById(id).map(r -> {
            r.setHelpfulCount(r.getHelpfulCount() + 1);
            return ResponseEntity.ok(repository.save(r));
        }).orElse(ResponseEntity.notFound().build());
    }
}
