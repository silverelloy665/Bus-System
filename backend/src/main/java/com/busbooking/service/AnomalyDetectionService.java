package com.busbooking.service;

import com.busbooking.dao.AnomalyLogRepository;
import com.busbooking.dao.BookingRepository;
import com.busbooking.model.AnomalyLog;
import com.busbooking.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnomalyDetectionService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AnomalyLogRepository anomalyRepository;

    @Scheduled(fixedRate = 60000)
    public void detectAnomalies() {
        List<Booking> allBookings = bookingRepository.findAll();
        long now = System.currentTimeMillis();
        long tenMinsAgo = now - (10 * 60 * 1000);

        Map<Long, Integer> userBookingCounts = new HashMap<>();
        for (Booking b : allBookings) {
            if (b.getBookedAt() != null && b.getBookedAt() > tenMinsAgo) {
                userBookingCounts.put(b.getUserId(), userBookingCounts.getOrDefault(b.getUserId(), 0) + 1);
            }
        }

        for (Map.Entry<Long, Integer> entry : userBookingCounts.entrySet()) {
            if (entry.getValue() >= 5) {
                String desc = "User " + entry.getKey() + " made " + entry.getValue() + " bookings in 10 mins. Possible bot/spam.";
                System.out.println("[ANOMALY] " + desc);
                anomalyRepository.save(new AnomalyLog("SPAM_BOOKINGS", desc));
            }
        }
    }

    public List<AnomalyLog> getAllAnomalies() {
        return anomalyRepository.findAll();
    }
}
