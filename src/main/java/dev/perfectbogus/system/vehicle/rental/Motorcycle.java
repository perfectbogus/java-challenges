package dev.perfectbogus.system.vehicle.rental;

public class Motorcycle extends Vehicle {

    private final double insurancePerDay = 15;
    private final boolean hasSideCar;

    public Motorcycle(String id, String brand, String model, int year, double baseDailyRate, boolean hasSideCar) {
        super(id, brand, model, year, baseDailyRate);
        this.hasSideCar = hasSideCar;
    }

    @Override
    public double getInsuranceCostPerDay() {
        if (hasSideCar) return insurancePerDay + 5;
        return insurancePerDay;
    }

    @Override
    public String getVehicleType() {
        return "MOTORCYCLE";
    }

    @Override
    protected double applyDiscount(double total, int days) {
        return total;
    }

}
