package dev.perfectbogus.system.vehicle.rental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private static final String ID = "T-001";
    private static final String BRAND = "brand-test";
    private static final String MODEL = "model-test";
    private static final int YEAR = java.time.Year.now().getValue();
    private static final double RATE = 50.0;
    private static final double INSURANCE = 10.0;

    private TestVehicle vehicle;

    @BeforeEach
    void setup() {
        vehicle = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
    }

    private static class TestVehicle extends Vehicle {
        TestVehicle(String id, String brand, String model, int year, double rate) {
            super(id, brand, model, year, rate);
        }

        @Override
        public double getInsuranceCostPerDay() {
            return INSURANCE;
        }

        @Override
        public String getVehicleType() {
            return "TEST";
        }

    }

    @Test
    @DisplayName("Create Vehicle and Available")
    void construct_newVehicle_isAvailable() {
        assertTrue(vehicle.isAvailable());
    }

    @Test
    @DisplayName("New Vehicle cannot be returned")
    void newVehicle_returnVehicle_throws() {
        assertThrows(IllegalStateException.class, vehicle::returnVehicle);
    }

    @Test
    @DisplayName("Cannot create a vehicle before 1990")
    void constructor_yearBefore1990_throws() {
        assertThrows(IllegalArgumentException.class, () -> vehicleWithYear(1989));
    }

    @Test
    @DisplayName("Cannot create a vehicle after current year")
    void constructor_yearAfterCurrent_throws() {
        final int year = java.time.Year.now().getValue() + 1;
        assertThrows(IllegalArgumentException.class, () -> vehicleWithYear(year));
    }

    @Test
    @DisplayName("Create a vehicle on 1990")
    void constructor_validYear_succeed() {
        assertDoesNotThrow(() -> vehicleWithYear(1990));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    @DisplayName("Invalid Rate on new vehicles")
    void constructor_invalidRate_throws(double rate) {
        assertThrows(IllegalArgumentException.class, () -> vehicleWithRate(rate));
    }

    @Test
    void constructor_smallValidRate_succeed() {
        double smallValidRate = 0.01;
        Vehicle t = vehicleWithRate(smallValidRate);
        assertEquals(smallValidRate, t.getBaseDailyRate(), 0.0001);
    }


    @Test
    @DisplayName("Positive valid rate on new vehicles")
    void constructor_validRate_createVehicle() {
        assertEquals(RATE, vehicle.getBaseDailyRate(), 0.0001);
    }

    @Test
    @DisplayName("Id Null not allowed")
    void constructor_nullId_throws() {
        assertThrows(NullPointerException.class, () -> new TestVehicle(null, BRAND, MODEL, YEAR, RATE));
    }

    @Test
    @DisplayName("Brand Null not allowed")
    void constructor_nullBrand_throws() {
        assertThrows(NullPointerException.class, () -> new TestVehicle(ID, null, MODEL, YEAR, RATE));
    }

    @Test
    @DisplayName("Model Null not allowed")
    void constructor_nullModel_throws() {
        assertThrows(NullPointerException.class, () -> new TestVehicle(ID, BRAND, null, YEAR, RATE));
    }

    @Test
    @DisplayName("Calculate Rental Cost under 7 days")
    void calculateRentalCost_underSevenDays_normalPrice() {
        assertEquals(360.0, vehicle.calculateRentalCost(6), 0.0001);
    }

    @Test
    @DisplayName("Calculate Rental Cost over 7 days or more days")
    void calculateRentalCost_sevenDays_applyDiscount() {
        assertEquals(378.0, vehicle.calculateRentalCost(7), 0.001);
    }

    @ParameterizedTest
    @ValueSource(ints = { -10, 0})
    @DisplayName("Calculate Rental Cost Not Allowed days")
    void calculateRentalCost_nonPositiveDays_throws(int days) {
        assertThrows(IllegalArgumentException.class, () -> vehicle.calculateRentalCost(days));
    }

    @Test
    @DisplayName("Try to rent a vehicle twice")
    void rent_twice_throws() {
        vehicle.rent();
        assertThrows(IllegalStateException.class, vehicle::rent);
        assertFalse(vehicle::isAvailable);
    }

    @Test
    @DisplayName("Complete circle, rent - return - rent again")
    void rent_afterReturn_succeeds() {
        vehicle.rent();
        assertFalse(vehicle.isAvailable());
        vehicle.returnVehicle();
        assertTrue(vehicle.isAvailable());
        vehicle.rent();
        assertFalse(vehicle.isAvailable());
    }

    @Test
    @DisplayName("Validate Format when Available")
    void toString_available_matchesExactFormat() {
        assertEquals("[TEST] brand-test model-test " + YEAR + " (ID: T-001) - $50.00/day - Available", vehicle.toString());
    }

    @Test
    @DisplayName("Validate Format when Rented")
    void toString_rented_matchesExactFormat() {
        vehicle.rent();
        assertEquals("[TEST] brand-test model-test " + YEAR + " (ID: T-001) - $50.00/day - Rented", vehicle.toString());
    }

    @Test
    @DisplayName("Rent when hook rejects throws illegal state exception")
    void rent_whenHookRejects_throwsAndStaysAvailable() {
        Vehicle picky = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE) {
            @Override
            protected void checkCanBeRented() {
                throw new IllegalStateException("not today");
            }
        };
        assertThrows(IllegalStateException.class, picky::rent);
        assertTrue(picky.isAvailable());
    }

    @Test
    void calculateRentalCost_passesTotalAndDaysToHook() {
        double[] receivedTotal = new double[1];
        int[] receivedDays = new int[1];

        Vehicle spy = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE) {
            @Override
            protected double applyDiscount(double total, int days) {
                receivedTotal[0] = total;
                receivedDays[0] = days;
                return 123.45;
            }
        };

        assertEquals(123.45, spy.calculateRentalCost(3), 0.001);
        assertEquals(180.0, receivedTotal[0], 0.001);
        assertEquals(3, receivedDays[0]);
    }

    private TestVehicle vehicleWithYear(int year) {
        return new TestVehicle(ID, BRAND, MODEL, year, RATE);
    }

    private TestVehicle vehicleWithRate(double rate) {
        return new TestVehicle(ID, BRAND, MODEL, YEAR, rate);
    }
}