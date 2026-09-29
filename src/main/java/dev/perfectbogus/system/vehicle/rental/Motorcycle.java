package dev.perfectbogus.system.vehicle.rental;

public class Motorcycle extends Vehicle {

    private static final double INSURANCE_PER_DAY = 15.0;
    private static final double SIDECAR_SURCHARGE = 5.0;
    private final boolean hasSideCar;

    public Motorcycle(String id, String brand, String model, int year, double baseDailyRate, boolean hasSideCar) {
        super(id, brand, model, year, baseDailyRate);
        this.hasSideCar = hasSideCar;
    }

    public boolean hasSideCar() {
        return hasSideCar;
    }

    @Override
    public double getInsuranceCostPerDay() {
        if (hasSideCar) return INSURANCE_PER_DAY + SIDECAR_SURCHARGE;
        return INSURANCE_PER_DAY;
    }

    @Override
    public String getVehicleType() {
        return "MOTORCYCLE";
    }

    @Override
    protected double applyDiscount(double total, int days) {
        return total;
    }

    @Override
    public void checkCanBeRented() {
        // Nothing to check
    }

}
