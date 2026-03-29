package com.busbooking.controller;

import com.busbooking.model.Booking;
import com.busbooking.service.BookingService;
import com.busbooking.service.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")

@SuppressWarnings("null")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private LoyaltyService loyaltyService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking saved = bookingService.createBooking(booking);
        if (saved.getFare() != null && saved.getFare() > 0) {
            loyaltyService.earnPointsFromBooking(saved.getUserId(), saved.getFare());
        }
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookingService.cancelBooking(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }
}
