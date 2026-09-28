package dev.perfectbogus.system.vehicle.rental;

public class Car extends Vehicle {

    private final int numberOfDoors;
    private static final double INSURANCE_PER_DAY = 10;

    public Car(String id, String brand, String model, int year, double baseDailyRate, int numberOfDoors) {
        super(id, brand, model, year, baseDailyRate);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public double getInsuranceCostPerDay() {
        return INSURANCE_PER_DAY;
    }

    @Override
    public String getVehicleType() {
        return "CAR";
    }
}
