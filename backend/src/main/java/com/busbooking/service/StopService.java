package com.busbooking.service;

import com.busbooking.dao.StopRepository;
import com.busbooking.model.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopService {

    @Autowired
    private StopRepository stopRepository;

    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    public Stop addStop(Stop stop) {
        stop.setIsActive(true);
        return stopRepository.save(stop);
    }
}
