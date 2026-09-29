package dev.perfectbogus.system.vehicle.rental;

import dev.perfectbogus.system.vehicle.rental.interfaces.Electric;
import dev.perfectbogus.system.vehicle.rental.interfaces.GpsTrackable;

public class ElectricCar extends Car implements Electric, GpsTrackable {

    private int currentBatteryLevel;
    private static final int KM_PER_BATTERY_LEVEL = 3;
    private final LocationTracker tracker = new LocationTracker();

    public ElectricCar(String id, String brand, String model, int year, double baseDailyRate, int numberOfDoors, int currentBatteryLevel) {
        super(id, brand, model, year, baseDailyRate, numberOfDoors);
        if (currentBatteryLevel < 0 || currentBatteryLevel > 100) throw new IllegalArgumentException("Battery Level range 0 and 100");
        this.currentBatteryLevel = currentBatteryLevel;
    }

    public boolean isFullyCharged() {
        return currentBatteryLevel == 100;
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
        return currentBatteryLevel * KM_PER_BATTERY_LEVEL;
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
    protected void checkCanBeRented() {
        if (currentBatteryLevel < MIN_BATTERY_TO_RENT)
            throw new IllegalStateException(
                    "Vehicle " + getId() + " battery too low " + currentBatteryLevel + "%");
    }
}