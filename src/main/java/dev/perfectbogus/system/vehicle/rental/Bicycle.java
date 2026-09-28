package dev.perfectbogus.system.vehicle.rental;

import dev.perfectbogus.system.vehicle.rental.interfaces.GpsTrackable;

public class Bicycle implements GpsTrackable {

    private String currentLocation;

    public Bicycle() {

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
