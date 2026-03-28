package com.busbooking.dao;

import com.busbooking.model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Fare entity
 * Provides database operations for Fare management
 */
@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {
}
