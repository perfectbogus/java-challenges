package dev.perfectbogus.system.vehicle.rental;

public class Truck extends Vehicle {

    private final double cargoCapacityKg;
    private final double insurancePerDay = 20.0;

    Truck(String id, String brand, String model, int year, double baseDailyRate, double cargoCapacityKg) {
        super(id, brand, model, year, baseDailyRate);
        this.cargoCapacityKg = cargoCapacityKg;
    }

    @Override
    double getInsuranceCostPerDay() {
        return insurancePerDay + getExtraPerCapacity();
    }

    private int getExtraPerCapacity() {
        return (int) cargoCapacityKg / 1000;
    }

    @Override
    String getVehicleType() {
        return "TRUCK";
    }

    @Override
    double applyDiscount(double total, int days) {
        if (days >= 5) return total * 0.85;
        return total;
    }
}
