package dev.perfectbogus.system.vehicle.rental;

import dev.perfectbogus.system.vehicle.rental.interfaces.GpsTrackable;
import dev.perfectbogus.system.vehicle.rental.interfaces.Maintainable;

public class Truck extends Vehicle implements GpsTrackable, Maintainable {

    private final double cargoCapacityKg;
    private static final double INSURANCE_PER_DAY = 20.0;
    private static final int LONG_RENTAL_DAYS = 5;
    private static final double DISCOUNT_LONG_RENTAL_DAYS = 0.85;
    private static final double KG_PER_INSURANCE_DOLLAR = 1000.0;
    private int kmsSinceService;
    private final LocationTracker tracker = new LocationTracker();

    public Truck(String id, String brand, String model, int year, double baseDailyRate, double cargoCapacityKg) {
        super(id, brand, model, year, baseDailyRate);
        if (cargoCapacityKg <= 0) throw new IllegalArgumentException("Capacity must be positive");
        this.cargoCapacityKg = cargoCapacityKg;
    }

    // Vehicle
    @Override
    public double getInsuranceCostPerDay() {
        return INSURANCE_PER_DAY + getExtraPerCapacity();
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

    // GpsTrackable
    @Override
    public String getCurrentLocation() {
        return tracker.get();
    }

    @Override
    public void updateLocation(String location) {
        tracker.update(location);
    }

    //Maintainable
    @Override
    public int getKilometersSinceService() {
        return kmsSinceService;
    }

    @Override
    public void performService() {
        this.kmsSinceService = 0;
    }

    @Override
    public void addKilometers(int kilometers) {
        if (kilometers <= 0) throw new IllegalArgumentException("km must be positive");
        this.kmsSinceService += kilometers;
    }

    /**
     * 2500 kg truck pay $2.50
     * 1000 kg truck pay $1.00
     */
    private double getExtraPerCapacity() {
        return cargoCapacityKg / KG_PER_INSURANCE_DOLLAR;
    }

}
