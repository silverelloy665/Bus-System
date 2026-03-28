package com.busbooking.service;
import com.busbooking.dao.BookingRepository;
import com.busbooking.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    public Booking cancelBooking(Long bookingId) {
        Optional<Booking> opt = bookingRepository.findById(bookingId);
        if (opt.isPresent()) {
            return bookingRepository.save(opt.get());
        }
        throw new RuntimeException("Booking not found");
    }
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findAll().stream()
            .filter(b -> userId.equals(b.getUserId()))
            .collect(Collectors.toList());
    }
}
