package dev.perfectbogus.system.vehicle.rental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MotorcycleTest {
    private static final String ID = "M-001";
    private static final String BRAND = "brand-test";
    private static final String MODEL = "model-test";
    private static final int YEAR = java.time.Year.now().getValue();
    private static final double BASE_DAILY_RATE = 50.0;

    private Motorcycle motorcycle;

    @BeforeEach
    void setup() {
        motorcycle = motorcycleWithoutSideCar();
    }

    @Test
    @DisplayName("3-day rental charges rate plus insurance")
    void calculateRentalCost_threeDays_returnsRatePlusInsurance() {
        assertEquals(195.0, motorcycle.calculateRentalCost(3), 0.001);
    }

    @Test
    @DisplayName("3-day rental charges rate plus insurance plus sidecar surcharge")
    void calculateRentalCost_threeDays_returnsRatePlusInsurancePlusSidecarSurcharge() {
        assertEquals(210.0, motorcycleWithSideCar().calculateRentalCost(3), 0.001);
    }

    @ParameterizedTest(name = "{0} days -> ${1}")
    @CsvSource({
            "1, 65.0",
            "7, 455.0",
            "10, 650.0",
            "30, 1950.0"
    })
    void calculateRentalCost_anyDays_neverDiscounted(int days, double expected) {
        assertEquals(expected, motorcycle.calculateRentalCost(days), 0.001);
    }

    private Motorcycle motorcycleWithSideCar() {
        return new Motorcycle(ID, BRAND, MODEL, YEAR, BASE_DAILY_RATE, true);
    }

    private Motorcycle motorcycleWithoutSideCar() {
        return new Motorcycle(ID, BRAND, MODEL, YEAR, BASE_DAILY_RATE, false);
    }

}