package dev.perfectbogus.system.vehicle.rental;

import dev.perfectbogus.system.vehicle.rental.interfaces.GpsTrackable;

public class Bicycle implements GpsTrackable {

    private String currentLocation;
    private final LocationTracker tracker = new LocationTracker();

    @Override
    public String getCurrentLocation() {
        return tracker.get();
    }

    @Override
    public void updateLocation(String location) {
        tracker.update(location);
    }
}
