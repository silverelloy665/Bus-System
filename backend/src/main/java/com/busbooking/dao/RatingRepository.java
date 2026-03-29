package com.busbooking.dao;

import com.busbooking.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByBusId(Long busId);
    List<Rating> findByDriverId(Long driverId);
}