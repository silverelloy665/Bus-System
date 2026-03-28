package com.busbooking.dao;

import com.busbooking.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Bus entity
 * Provides database operations for Bus management
 */
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
}
