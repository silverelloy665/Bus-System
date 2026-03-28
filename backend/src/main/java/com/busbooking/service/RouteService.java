package com.busbooking.service;

import com.busbooking.dao.RouteRepository;
import com.busbooking.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route addRoute(Route route) {
        route.setIsActive(true);
        return routeRepository.save(route);
    }
}
