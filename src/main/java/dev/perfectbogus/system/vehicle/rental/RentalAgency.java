package dev.perfectbogus.system.vehicle.rental;

import java.util.List;
import java.util.Optional;

public class RentalAgency {
    List<Vehicle> fleet;

    public boolean addVehicle(Vehicle v) {
        if (fleet.contains(v)) return false;
        return fleet.add(v);
    }

    public List<Vehicle> getAvailableVehicles() {
        return fleet.stream().filter(Vehicle::isAvailable).toList();
    }

    public double rentVehicle(String id, int days) {
        Vehicle toRent = fleet.stream()
                .findFirst()
                .filter(v -> v.getId().equals(id) && v.isAvailable())
                .orElseThrow(() -> new RuntimeException("Not available vehicle: " + id));
        toRent.rent();
        double subTotalDaily = toRent.getBaseDailyRate() * days;
        double subTotalInsurance = toRent.getInsuranceCostPerDay() * days;
        return subTotalDaily + subTotalInsurance;
    }
}
