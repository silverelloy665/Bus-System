package com.busbooking.dao;

import com.busbooking.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Stop entity
 * Provides database operations for Stop management
 */
@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {
}
