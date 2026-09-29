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

        @Override
        public void checkCanBeRented() {
            // Nothing to check
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
    @DisplayName("Create Vehicle with Current year")
    void constructor_currentYear_ok() {
        final int year = java.time.Year.now().getValue();
        assertNotNull(vehicleWithYear(year));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0})
    @DisplayName("Invalid Rate on new vehicles")
    void constructor_invalidRate_throws(double rate) {
        assertThrows(IllegalArgumentException.class, () -> vehicleWithRate(rate));
    }


    @Test
    @DisplayName("Positive valid rate on new vehicles")
    void constructor_validRate_createVehicle() {
        assertEquals(RATE, vehicle.getBaseDailyRate());
    }

    @Test
    @DisplayName("Id Null not allowed")
    void constructor_nullId_throws() {
        assertThrows(NullPointerException.class, () -> new TestVehicle(null, BRAND, MODEL, 2000, 0.5));
    }

    @Test
    @DisplayName("Brand Null not allowed")
    void constructor_nullBrand_throws() {
        assertThrows(NullPointerException.class, () -> new TestVehicle(ID, null, MODEL, 2000, 0.5));
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
    @DisplayName("Calculate Rental Cost over 7 days")
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
    @DisplayName("Calculate Insurance Cost Per Day")
    void getInsuranceCostPerDay_ok() {
        assertEquals(INSURANCE, vehicle.getInsuranceCostPerDay());
    }

    @Test
    @DisplayName("Try to rent a vehicle twice")
    void rent_twice_throws() {
        vehicle.rent();
        assertThrows(IllegalStateException.class, vehicle::rent);
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
    @DisplayName("Validate Format")
    void toString_available_matchesExactFormat() {
        assertEquals("[TEST] brand-test model-test " + YEAR + " (ID: T-001) - $50.00/day - Available", vehicle.toString());
    }

    @Test
    @DisplayName("Rent when hook rejects throws illegal state exception")
    void rent_whenHookRejects_throwsAndStaysAvailable() {
        Vehicle picky = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE) {
            @Override
            public void checkCanBeRented() {
                throw new IllegalStateException("not today");
            }
        };
        assertThrows(IllegalStateException.class, picky::rent);
        assertTrue(picky.isAvailable());
    }

    @Test
    @DisplayName("Calculate Rental Cost when hook fixed return fixed value")
    void calculateRentalCost_whenHookFixed_returnFixed() {
        double FIXED = 0.0;
        double INSURANCE = 10.0;
        Vehicle anonymous = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE) {
            @Override
            public double getInsuranceCostPerDay() {
                return INSURANCE;
            }

            @Override
            public double applyDiscount(double total , int days) {
                return FIXED;
            }
        };

        assertEquals(FIXED, anonymous.calculateRentalCost(3));
    }

    private TestVehicle vehicleWithYear(int year) {
        return new TestVehicle(ID, BRAND, MODEL, year, RATE);
    }

    private TestVehicle vehicleWithRate(double rate) {
        return new TestVehicle(ID, BRAND, MODEL, YEAR, rate);
    }
}