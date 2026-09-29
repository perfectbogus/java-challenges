package dev.perfectbogus.system.vehicle.rental;

import java.util.Objects;

public abstract class Vehicle {
    private final String id;
    private final String brand;
    private final String model;
    private final int year;
    private final double baseDailyRate;
    private boolean available = true;
    private static final int LONG_RENTAL_DAYS = 7;
    private static final double DISCOUNT_LONG_RENTAL_DAYS = 0.90;

    protected Vehicle(String id, String brand, String model, int year, double baseDailyRate) {
        int currentYear = java.time.Year.now().getValue();
        if (year < 1990 || year > currentYear) throw new IllegalArgumentException("Year cannot be before 1990 or after " + currentYear);
        if (baseDailyRate <= 0) throw new IllegalArgumentException("baseDailyRate must be greater than 0");

        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.brand = Objects.requireNonNull(brand, "brand cannot be null");
        this.model = Objects.requireNonNull(model, "model cannot be null");
        this.year = year;
        this.baseDailyRate = baseDailyRate;
    }

    public abstract double getInsuranceCostPerDay();
    public abstract String getVehicleType();
    public abstract void checkCanBeRented();

    public final double calculateRentalCost(int days) {
        if(days <= 0) throw new IllegalArgumentException("days must be positive");
        double total = (baseDailyRate + getInsuranceCostPerDay()) * days;
        return applyDiscount(total, days);
    }

    protected double applyDiscount(double total, int days) {
        if (days <= 0) throw new IllegalArgumentException("days must be positive");
        if (days >= LONG_RENTAL_DAYS) return total * DISCOUNT_LONG_RENTAL_DAYS;
        return total;
    }

    public final void rent() {
        if (!available) throw new IllegalStateException("Vehicle " + id + " is already rented");
        checkCanBeRented();
        this.available = false;
    }

    public void returnVehicle() {
        if (available) throw new IllegalStateException("Vehicle " + id + " is available");
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
        return String.format("[%s] %s %s %d (ID: %s) - $%.2f/day - %s",
                getVehicleType(), brand, model, year, id, baseDailyRate,
                available ? "Available" : "Rented");
    }
}
