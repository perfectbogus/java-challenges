package dev.perfectbogus.system.vehicle.rental.interfaces;

public interface Maintainable {
    int KMS_PER_SERVICE = 10000;
    int getKilometersSinceService();
    void performService();
    default boolean needsService() {
        return getKilometersSinceService() > KMS_PER_SERVICE;
    }
}
