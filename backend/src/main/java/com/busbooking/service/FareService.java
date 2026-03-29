package com.busbooking.service;
import com.busbooking.dao.FareRepository;
import com.busbooking.dao.BusRepository;
import com.busbooking.model.Fare;
import com.busbooking.model.Bus;
import com.busbooking.dto.SmartFareResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class FareService {
    @Autowired
    private FareRepository fareRepository;

    @Autowired
    private BusRepository busRepository;

    public SmartFareResponse calculateFare(Long routeId, Long busId) {
        List<Fare> fares = fareRepository.findAll();
        Optional<Fare> opt = fares.stream()
            .filter(f -> routeId.equals(f.getRouteId()))
            .findFirst();
        if (opt.isPresent()) {
            Fare f = opt.get();
            int hour = java.time.LocalTime.now().getHour();
            boolean isPeak = (hour >= 8 && hour <= 10) || (hour >= 17 && hour <= 20);
            double baseTimeFare = isPeak ? f.getBaseFare() * f.getPeakMultiplier() : f.getBaseFare() * f.getOffPeakMultiplier();

            SmartFareResponse response = new SmartFareResponse();
            response.setStatus("NORMAL");
            response.setMessage("Standard fare");
            response.setFare(baseTimeFare);

            if (busId != null) {
                Optional<Bus> busOpt = busRepository.findById(busId);
                if (busOpt.isPresent()) {
                    Bus bus = busOpt.get();
                    if (bus.getCapacity() != null && bus.getCapacity() > 0) {
                        double occupancyRate = (double) bus.getPassengerCount() / bus.getCapacity();
                        if (occupancyRate > 0.8) {
                            response.setFare(baseTimeFare * 1.3);
                            response.setStatus("SURGE");
                            response.setMessage("🔴 High Demand - Surge pricing active");
                        } else if (occupancyRate > 0.5) {
                            response.setFare(baseTimeFare * 1.1);
                            response.setStatus("SURGE_LOW");
                            response.setMessage("🟡 Moderate Demand - Slight surge");
                        } else if (occupancyRate < 0.2) {
                            response.setFare(baseTimeFare * 0.9);
                            response.setStatus("DISCOUNT");
                            response.setMessage("🟢 Low Demand - Discount applied");
                        }
                    }
                }
            }

            return response;
        }
        return new SmartFareResponse(0.0, "NORMAL", "");
    }
    public double suggestFare(Long routeId) { return calculateFare(routeId, null).getFare(); }
    public String validateFareChange(Double oldFare, Double newFare) {
        if (newFare < oldFare * 0.75) return "WARNING: Loss";
        if (newFare > oldFare * 1.25) return "WARNING: Unethical - may reduce demand";
        return "OK";
    }
    @SuppressWarnings("null")
    public Fare saveFare(Fare fare) {
        return fareRepository.save(fare);
    }
}
