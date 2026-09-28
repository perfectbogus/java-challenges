package dev.perfectbogus.system.vehicle.rental;

public abstract class Vehicle {
    private final String id;
    private final String brand;
    private final String model;
    private final int year;
    private double baseDailyRate;
    private boolean available = true;
    private static final int LONG_RENTAL_DAYS = 7;
    private static final double DISCOUNT_LONG_RENTAL_DAYS = 0.90;

    protected Vehicle(String id, String brand, String model, int year, double baseDailyRate) {
        int currentYear = java.time.Year.now().getValue();
        if (year < 1990 || year > currentYear) throw new IllegalArgumentException("Year cannot be before 1990");
        if (baseDailyRate <= 0) throw new IllegalArgumentException("baseDailyRate must be greater than 0");

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.baseDailyRate = baseDailyRate;
    }

    public abstract double getInsuranceCostPerDay();
    public abstract String getVehicleType();

    public final double calculateRentalCost(int days) {
        double total = (baseDailyRate + getInsuranceCostPerDay()) * days;
        return applyDiscount(total, days);
    }

    protected double applyDiscount(double total, int days) {
        if (days >= LONG_RENTAL_DAYS) return total * DISCOUNT_LONG_RENTAL_DAYS;
        return total;
    }

    public void rent() {
        if (!available) throw new IllegalStateException("Vehicle cannot be rent");
        this.available = false;
    }

    public void returnVehicle() {
        available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getId() {
        return id;
    }

    public double getBaseDailyRate() {
        return baseDailyRate;
    }

    @Override
    public String toString() {
        return "[ " + getVehicleType() + "] "
                + model
                + " " + brand
                + " " + year
                + " (ID: " + id + ") - "
                + baseDailyRate + "/day - "
                + available;
    }
}
