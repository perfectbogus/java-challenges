package dev.perfectbogus.system.vehicle.rental;

import dev.perfectbogus.system.vehicle.rental.interfaces.Electric;
import dev.perfectbogus.system.vehicle.rental.interfaces.GpsTrackable;

public class ElectricCar extends Car implements Electric, GpsTrackable {

    private int currentBatteryLevel;
    private String currentLocation;
    private final LocationTracker tracker = new LocationTracker();

    public ElectricCar(String id, String brand, String model, int year, double baseDailyRate, int numberOfDoors) {
        super(id, brand, model, year, baseDailyRate, numberOfDoors);
        this.currentBatteryLevel = 100;// Missing Requirement
    }

    @Override
    public int getBatteryLevel() {
        return currentBatteryLevel;
    }

    @Override
    public void charge(int percent) {
        if (percent < 0) throw new IllegalArgumentException("percent must be positive");
        currentBatteryLevel = Math.min(100, currentBatteryLevel + percent);
    }

    @Override
    public int getRangeKm() {
        return 0; // Missing Requirement
    }

    @Override
    public String getCurrentLocation() {
        return tracker.get();
    }

    @Override
    public void updateLocation(String location) {
        tracker.update(location);
    }

    @Override
    public void checkCanBeRented() {
        if (currentBatteryLevel < MIN_BATTERY_TO_RENT)
            throw new IllegalStateException(
                    "Vehicle " + getId() + " battery too low " + currentBatteryLevel + "%");
    }
}