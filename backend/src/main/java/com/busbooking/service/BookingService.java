package com.busbooking.service;

import com.busbooking.dao.BookingRepository;
import com.busbooking.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for Booking business logic
 * Handles booking creation, cancellation, and retrieval
 */
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Create a new booking
     * @param booking The booking object with userId, busId, fromStopId, toStopId
     * @return The created booking with bookingId
     */
    public Booking createBooking(Booking booking) {
        booking.setBookedAt(System.currentTimeMillis());
        return bookingRepository.save(booking);
    }

    /**
     * Cancel an existing booking
     * @param bookingId The booking ID to cancel
     * @return true if cancellation was successful, false otherwise
     */
    public boolean cancelBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            bookingRepository.deleteById(bookingId);
            return true;
        }
        return false;
    }

    /**
     * Get all bookings for a specific user
     * @param userId The user ID
     * @return List of bookings for the user
     */
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findAll().stream()
                .filter(booking -> booking.getUserId() != null && booking.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
