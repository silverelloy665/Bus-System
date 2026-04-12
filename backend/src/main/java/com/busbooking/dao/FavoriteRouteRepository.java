package com.busbooking.dao;

import com.busbooking.model.FavoriteRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRouteRepository extends JpaRepository<FavoriteRoute, Long> {
    List<FavoriteRoute> findByUserId(Long userId);
}
