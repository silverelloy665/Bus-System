package com.busbooking.dao;

import com.busbooking.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Route entity
 * Provides database operations for Route management
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
