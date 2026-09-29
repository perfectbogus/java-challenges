package dev.perfectbogus.system.vehicle.rental;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private final static String ID = "T-001";
    private final static String BRAND = "brand-test";
    private final static String MODEL = "model-test";
    private final static int YEAR = 2000;
    private final static double RATE = 50.0;
    private final static double INSURANCE = 10.0;

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
    void construct_newVehicle_isAvailable() {
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
        assertTrue(t.isAvailable());
    }

    @Test
    void newVehicle_returnVehicle_throws() {
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
        assertThrows(IllegalStateException.class, t::returnVehicle);
    }

    @Test
    void constructor_yearBefore1990_throws() {
        final double rate = 40.0;
        final int year = 1989;
        assertThrows(IllegalArgumentException.class, () -> new TestVehicle(ID, BRAND, MODEL, year, rate));
    }

    @Test
    void constructor_yearAfterCurrent_throws() {
        final int year = java.time.Year.now().getValue() + 1;
        assertThrows(IllegalArgumentException.class, () -> new TestVehicle(ID, BRAND, MODEL, year, 40.0));
    }

    @Test
    void constructor_currentYear_ok() {
        final int year = java.time.Year.now().getValue();
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL, year, 40.0);
        assertNotNull(t);
    }

    @Test
    void constructor_rateNegative_throws() {
        final int currentYear = java.time.Year.now().getValue();
        assertThrows(IllegalArgumentException.class, () -> new TestVehicle(ID, BRAND, MODEL, currentYear, -10));
    }

    @Test
    void constructor_zeroRate_throws() {
        final int year = java.time.Year.now().getValue();
        assertThrows(IllegalArgumentException.class, () -> new TestVehicle(ID, BRAND, MODEL, year, 0));
    }

    @Test
    void constructor_validRate_createVehicle() {
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL,2000, 50.0);
        assertEquals(50.0, t.getBaseDailyRate(), 0.0001);
    }

    @Test
    void constructor_nullId_throws() {
        assertThrows(NullPointerException.class, () -> new TestVehicle(null, BRAND, MODEL, 2000, 0.5));
    }

    @Test
    void constructor_nullBrand_throws() {
        assertThrows(NullPointerException.class, () -> new TestVehicle(ID, null, MODEL, 2000, 0.5));
    }

    @Test
    void constructor_nullModel_throws() {
        assertThrows(NullPointerException.class, () -> new TestVehicle(ID, BRAND, null, YEAR, RATE));
    }

    @Test
    void calculateRentalCost_underSevenDays_normalPrice() {
        TestVehicle testVehicle = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
        double total = testVehicle.calculateRentalCost(6);
        assertEquals(360.0, total, 0.0001);
    }

    @Test
    void calculateRentalCost_sevenDays_applyDiscount() {
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
        double total = t.calculateRentalCost(7);
        assertEquals(378.0, total, 0.001);
    }

    @ParameterizedTest
    @ValueSource(ints = { -10, 0})
    void calculateRentalCost_negativeDays_throws(int days) {
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
        assertThrows(IllegalArgumentException.class, () -> t.calculateRentalCost(days));
    }

    @Test
    void getInsuranceCostPerDay_ok() {
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
        assertEquals(INSURANCE, t.getInsuranceCostPerDay());
    }

    @Test
    void rent_twice_throws() {
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
        t.rent();
        assertThrows(IllegalStateException.class, t::rent);
    }

    @Test
    void rent_return_ok() {
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
        t.rent();
        assertFalse(t.isAvailable());
        t.returnVehicle();
        assertTrue(t.isAvailable());
        t.rent();
        assertFalse(t.isAvailable());
    }

    @Test
    void toString_available_matchesExactFormat() {
        TestVehicle t = new TestVehicle(ID, BRAND, MODEL, YEAR, RATE);
        assertEquals("[TEST] brand-test model-test 2000 (ID: T-001) - $50.00/day - Available", t.toString());
    }
}