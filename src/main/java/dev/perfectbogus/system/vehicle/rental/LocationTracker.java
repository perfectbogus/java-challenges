package dev.perfectbogus.system.vehicle.rental;

import java.util.Objects;

public class LocationTracker {
    private String location = "Unknown";

    public String get() {
        return location;
    }

    public void update(String newLocation) {
        this.location = Objects.requireNonNull(newLocation, "Location cannot be null");
    }

}
