package com.busbooking.dao;

import com.busbooking.model.RouteDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface RouteDemandRepository extends JpaRepository<RouteDemand, Long> {
    Optional<RouteDemand> findByRouteIdAndDayOfWeekAndHourOfDay(Long routeId, Integer dayOfWeek, Integer hourOfDay);
    List<RouteDemand> findByDayOfWeekAndHourOfDay(Integer dayOfWeek, Integer hourOfDay);
    List<RouteDemand> findByRouteId(Long routeId);
}
