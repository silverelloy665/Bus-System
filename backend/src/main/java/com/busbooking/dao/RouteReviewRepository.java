package com.busbooking.dao;

import com.busbooking.model.RouteReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteReviewRepository extends JpaRepository<RouteReview, Long> {
    List<RouteReview> findByRouteIdOrderByTimestampDesc(Long routeId);
    List<RouteReview> findByRouteIdOrderByHelpfulCountDesc(Long routeId);
}
