package com.busbooking.service;
import com.busbooking.dao.BusRepository;
import com.busbooking.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@SuppressWarnings("null")
public class BusService {
    @Autowired
    private BusRepository busRepository;
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
    public List<Bus> getBusesByRoute(Long routeId) {
        return busRepository.findAll().stream()
            .filter(bus -> routeId.equals(bus.getRouteId()))
            .collect(Collectors.toList());
    }
    public Bus updateBusLocation(Long busId, Double lat, Double lng) {
        Optional<Bus> opt = busRepository.findById(busId);
        if (opt.isPresent()) {
            Bus b = opt.get();
            b.setCurrentLat(lat);
            b.setCurrentLng(lng);
            return busRepository.save(b);
        }
        throw new RuntimeException("Bus not found");
    }

    public Bus driverUpdate(Long busId, String driverCode, Double lat, Double lng, Integer passengerCount, String status) {
        Optional<Bus> opt = busRepository.findById(busId);
        if (opt.isPresent()) {
            Bus b = opt.get();
            if (driverCode != null && b.getDriverCode() != null && !b.getDriverCode().equals(driverCode)) {
                throw new RuntimeException("Invalid driver code");
            }
            if (lat != null) b.setCurrentLat(lat);
            if (lng != null) b.setCurrentLng(lng);
            if (passengerCount != null) b.setPassengerCount(passengerCount);
            if (status != null && !status.isEmpty()) b.setStatus(status);
            return busRepository.save(b);
        }
        throw new RuntimeException("Bus not found");
    }
    public List<Bus> getNearbyBuses(Double lat, Double lng) {
        return busRepository.findAll().stream()
            .filter(bus -> bus.getCurrentLat() != null && bus.getCurrentLng() != null)
            .filter(bus -> Math.sqrt(Math.pow(bus.getCurrentLat()-lat,2)+Math.pow(bus.getCurrentLng()-lng,2)) < 0.05)
            .collect(Collectors.toList());
    }
}
