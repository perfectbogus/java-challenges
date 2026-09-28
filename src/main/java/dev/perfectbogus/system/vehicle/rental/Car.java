package dev.perfectbogus.system.vehicle.rental;

public class Car extends Vehicle {

    private final int numberOfDoors;
    private final double insurancePerDay = 10;

    Car(String id, String brand, String model, int year, double baseDailyRate, int numberOfDoors) {
        super(id, brand, model, year, baseDailyRate);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    double getInsuranceCostPerDay() {
        return this.insurancePerDay;
    }

    @Override
    String getVehicleType() {
        return "CAR";
    }
}
