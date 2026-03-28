package com.busbooking.service;

import com.busbooking.dao.RouteRepository;
import com.busbooking.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("null")
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route createRoute(Route route) {
        route.setIsActive(true);
        return routeRepository.save(route);
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}
