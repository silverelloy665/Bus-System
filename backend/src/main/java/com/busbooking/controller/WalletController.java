package com.busbooking.controller;

import com.busbooking.model.WalletTransaction;
import com.busbooking.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/recharge")
    public ResponseEntity<Double> recharge(@RequestBody WalletRequest request) {
        try {
            return ResponseEntity.ok(walletService.recharge(request.getUserId(), request.getAmount(), request.getPaymentMethod()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(walletService.getBalance(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/deduct")
    public ResponseEntity<Double> deduct(@RequestBody WalletRequest request) {
        try {
            return ResponseEntity.ok(walletService.deduct(request.getUserId(), request.getAmount(), "Booking Deduction"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<WalletTransaction>> getTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getTransactions(userId));
    }
}
