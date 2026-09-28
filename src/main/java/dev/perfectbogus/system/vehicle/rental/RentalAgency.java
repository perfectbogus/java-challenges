package dev.perfectbogus.system.vehicle.rental;

import dev.perfectbogus.system.vehicle.rental.interfaces.Electric;
import dev.perfectbogus.system.vehicle.rental.interfaces.Maintainable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RentalAgency {
    List<Vehicle> fleet = new ArrayList<>();

    public boolean addVehicle(Vehicle v) {
        Objects.requireNonNull(v, "Vehicle cannot be null");
        boolean duplicate = fleet.stream().anyMatch(existing -> existing.getId().equals(v.getId()));

        if (duplicate) return false;

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

    public List<Maintainable> getVehiclesNeedingService() {
        return fleet.stream()
                .filter(Maintainable.class::isInstance)
                .map(Maintainable.class::cast)
                .filter(Maintainable::needsService)
                .toList();
    }

    public void chargeAllElectric(int percent) {
        fleet.stream()
                .filter(Electric.class::isInstance)
                .map(Electric.class::cast)
                .forEach(e -> e.charge(percent));
    }

    public void printFleetReport() {
        fleet.forEach(System.out::println);
    }
}

