package com.busbooking.controller;

import com.busbooking.model.LoyaltyTransaction;
import com.busbooking.service.LoyaltyService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loyalty")
public class LoyaltyController {

    @Autowired
    private LoyaltyService loyaltyService;

    @PostMapping("/redeem")
    public ResponseEntity<Integer> redeemPoints(@RequestBody RedeemRequest request) {
        try {
            return ResponseEntity.ok(loyaltyService.redeemPoints(request.getUserId(), request.getPoints()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Integer> getBalance(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(loyaltyService.getBalance(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/history")
    public ResponseEntity<List<LoyaltyTransaction>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(loyaltyService.getHistory(userId));
    }
}

@Data
class RedeemRequest {
    private Long userId;
    private Integer points;
}
