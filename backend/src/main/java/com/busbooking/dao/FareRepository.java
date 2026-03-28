package com.busbooking.dao;

import com.busbooking.model.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Fare entity
 * Provides database operations for Fare management
 */
@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {

    /**
     * Find all fares for a specific route
     * @param routeId The route ID
     * @return List of fares for the route
     */
    @Query("SELECT f FROM Fare f WHERE f.routeId = :routeId")
    List<Fare> findByRouteId(@Param("routeId") Long routeId);
}
