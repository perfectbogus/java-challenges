package dev.perfectbogus.system.vehicle.rental.interfaces;

public interface GpsTrackable {
    String getCurrentLocation();
    void updateLocation(String location);
    static double distanceBetween(double lat1, double lon1, double lat2, double lon2) {
        double left = Math.pow(lat2 - lat1, 2);
        double right = Math.pow(lon2 - lon1, 2);
        return Math.sqrt(left + right);
    }
}
