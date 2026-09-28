package dev.perfectbogus.system.vehicle.rental.interfaces;

public interface Electric {

    int MIN_BATTERY_TO_RENT = 20;

    int getBatteryLevel();
    void charge(int percent);
    int getRangeKm();

    default boolean isFullyCharged() {
        return getBatteryLevel() == 100;
    }
}
