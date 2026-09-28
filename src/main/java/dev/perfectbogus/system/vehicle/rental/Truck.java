package dev.perfectbogus.system.vehicle.rental;

import dev.perfectbogus.system.vehicle.rental.interfaces.GpsTrackable;
import dev.perfectbogus.system.vehicle.rental.interfaces.Maintainable;

public class Truck extends Vehicle implements GpsTrackable, Maintainable {

    private final double cargoCapacityKg;
    private static final double INSURANCE_PER_DAY = 20.0;
    private static final int LONG_RENTAL_DAYS = 5;
    private static final double DISCOUNT_LONG_RENTAL_DAYS = 0.85;
    private String currentLocation;
    private int kmsSinceService;

    public Truck(String id, String brand, String model, int year, double baseDailyRate, double cargoCapacityKg) {
        super(id, brand, model, year, baseDailyRate);
        this.cargoCapacityKg = cargoCapacityKg;
        this.currentLocation = ""; //Missing requirement
        this.kmsSinceService = 0; //Missing requirement
    }

    @Override
    public double getInsuranceCostPerDay() {
        return INSURANCE_PER_DAY + getExtraPerCapacity();
    }

    private int getExtraPerCapacity() {
        return (int) cargoCapacityKg / 1000;
    }

    public void addKilometers(int km) {
        if (km <= 0) throw new IllegalArgumentException("km must be positive");
        this.kmsSinceService += km;
    }

    @Override
    public String getVehicleType() {
        return "TRUCK";
    }

    @Override
    protected double applyDiscount(double total, int days) {
        if (days >= LONG_RENTAL_DAYS) return total * DISCOUNT_LONG_RENTAL_DAYS;
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
        return kmsSinceService;
    }

    @Override
    public void performService() {
        this.kmsSinceService = 0;
    }
}
