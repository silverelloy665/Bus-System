package com.busbooking.dao;

import com.busbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Booking entity
 * Provides database operations for Booking management
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
