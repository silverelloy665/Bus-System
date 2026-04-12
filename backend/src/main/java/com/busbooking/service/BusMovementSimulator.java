package com.busbooking.service;

import com.busbooking.controller.BusLocationWebSocket;
import com.busbooking.dao.BusRepository;
import com.busbooking.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BusMovementSimulator {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusLocationWebSocket busLocationWebSocket;

    private final Random random = new Random();

    @Scheduled(fixedRate = 3000)
    public void simulateLiveDatabaseUpdates() {
        List<Bus> buses = busRepository.findAll();
        boolean updated = false;
        for (Bus bus : buses) {
            if ("ACTIVE".equalsIgnoreCase(bus.getStatus()) && bus.getCurrentLat() != null && bus.getCurrentLng() != null) {
                double latChange = (random.nextDouble() - 0.5) * 0.001;
                double lngChange = (random.nextDouble() - 0.5) * 0.001;
                
                bus.setCurrentLat(bus.getCurrentLat() + latChange);
                bus.setCurrentLng(bus.getCurrentLng() + lngChange);
                
                busRepository.save(bus);
                busLocationWebSocket.broadcastBusLocation(bus);
                updated = true;
            }
        }
        if (updated) {
            System.out.println("Live Database updates pushed to Admin, Customer, and Driver UIs");
        }
    }
}
