package dev.perfectbogus.system.vehicle.rental;

import dev.perfectbogus.system.vehicle.rental.interfaces.GpsTrackable;
import dev.perfectbogus.system.vehicle.rental.interfaces.Maintainable;

public class Truck extends Vehicle implements GpsTrackable, Maintainable {

    private final double cargoCapacityKg;
    private final double insurancePerDay = 20.0;
    private String currentLocation;
    private int kmsSinceService;

    Truck(String id, String brand, String model, int year, double baseDailyRate, double cargoCapacityKg) {
        super(id, brand, model, year, baseDailyRate);
        this.cargoCapacityKg = cargoCapacityKg;
        this.currentLocation = ""; //Missing requirement
        this.kmsSinceService = 0; //Missing requirement
    }

    @Override
    double getInsuranceCostPerDay() {
        return insurancePerDay + getExtraPerCapacity();
    }

    private int getExtraPerCapacity() {
        return (int) cargoCapacityKg / 1000;
    }

    @Override
    String getVehicleType() {
        return "TRUCK";
    }

    @Override
    double applyDiscount(double total, int days) {
        if (days >= 5) return total * 0.85;
        return total;
    }

    @Override
    public String getCurrentLocation() {
        return this.currentLocation;
    }

    @Override
    public void updateLocation(String location) {
        this.currentLocation = location;
    }

    @Override
    public int getKilometersSinceService() {
        return 0; // Missing Requirement
    }

    @Override
    public void performService() {
        this.kmsSinceService = 0;
    }
}
