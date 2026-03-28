package com.busbooking.service;
import com.busbooking.dao.FareRepository;
import com.busbooking.model.Fare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class FareService {
    @Autowired
    private FareRepository fareRepository;
    public double calculateFare(Long routeId) {
        List<Fare> fares = fareRepository.findAll();
        Optional<Fare> opt = fares.stream()
            .filter(f -> routeId.equals(f.getRouteId()))
            .findFirst();
        if (opt.isPresent()) {
            Fare f = opt.get();
            int hour = java.time.LocalTime.now().getHour();
            boolean isPeak = (hour >= 8 && hour <= 10) || (hour >= 17 && hour <= 20);
            return isPeak ? f.getBaseFare() * f.getPeakMultiplier() : f.getBaseFare() * f.getOffPeakMultiplier();
        }
        return 0.0;
    }
    public double suggestFare(Long routeId) { return calculateFare(routeId); }
    public String validateFareChange(Double oldFare, Double newFare) {
        if (newFare < oldFare * 0.75) return "WARNING: Loss";
        if (newFare > oldFare * 1.25) return "WARNING: Unethical - may reduce demand";
        return "OK";
    }
}
