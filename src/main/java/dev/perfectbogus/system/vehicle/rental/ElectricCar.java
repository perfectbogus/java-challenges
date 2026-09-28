package dev.perfectbogus.system.vehicle.rental;

import dev.perfectbogus.system.vehicle.rental.interfaces.Electric;
import dev.perfectbogus.system.vehicle.rental.interfaces.GpsTrackable;

public class ElectricCar extends Car implements Electric, GpsTrackable {

    private int currentBatteryLevel;
    private String currentLocation;

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
        this.currentBatteryLevel = percent;
    }

    @Override
    public int getRangeKm() {
        return 0; // Missing Requirement
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String location) {
        this.currentLocation = location;
    }
}