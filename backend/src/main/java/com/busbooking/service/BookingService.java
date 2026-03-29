package com.busbooking.service;
import com.busbooking.dao.BookingRepository;
import com.busbooking.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.lang.NonNull;

@Service
@SuppressWarnings("null")
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LoyaltyService loyaltyService;

    public Booking createBooking(@NonNull Booking booking) {
        Booking saved = bookingRepository.save(booking);
        // Calculate points (1 point per 10 spent)
        if (saved.getFare() != null && saved.getFare() > 0) {
            loyaltyService.earnPointsFromBooking(saved.getUserId(), saved.getFare());
        }
        return saved;
    }
    public Booking cancelBooking(@NonNull Long bookingId) {
        Optional<Booking> opt = bookingRepository.findById(bookingId);
        if (opt.isPresent()) {
            Booking b = opt.get();
            return bookingRepository.save(b);
        }
        throw new RuntimeException("Booking not found");
    }
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findAll().stream()
            .filter(b -> userId.equals(b.getUserId()))
            .collect(Collectors.toList());
    }
}
