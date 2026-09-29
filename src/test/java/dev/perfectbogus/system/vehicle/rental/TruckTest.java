package dev.perfectbogus.system.vehicle.rental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TruckTest {

    private static final String ID = "T-001";
    private static final String BRAND = "T-Brand";
    private static final String MODEL = "T-Model";
    private static final int YEAR = java.time.Year.now().getValue();
    private static final double BASE_DAILY_RATE = 50.0;
    private static final double CARGO_CAPACITY_KG = 2500.0;

    private Truck truck;

    @BeforeEach
    void setup() {
        truck = new Truck(ID, BRAND, MODEL, YEAR, BASE_DAILY_RATE, CARGO_CAPACITY_KG);
    }

    @Test
    @DisplayName("Insurance for 2500 Kg -> 22.5")
    void getInsuranceCostPerDay_2500kg_returns22point5() {
        assertEquals(20 + 2.5, truck.getInsuranceCostPerDay(), 0.001);
    }

    @ParameterizedTest(name = "{0} days -> ${1}")
    @CsvSource({
            "4, 290.0",  // no discount
            "5, 308.125", // the boundary: 15% off starts here
            "10, 616.25"
    })
    void calculateRentalCost_variousDays(int days, double expected) {
        assertEquals(expected, truck.calculateRentalCost(days), 0.001);
    }

    @ParameterizedTest
    @ValueSource(ints = { -10, 0})
    void addKilometers_nonPositive_throws(int kilometers) {
        assertThrows(IllegalArgumentException.class, () -> truck.addKilometers(kilometers));
    }

    @ParameterizedTest(name = "{0} kilometers -> need service? {1}")
    @CsvSource({
            "10000, false",
            "10001, true"
    })
    void addKilometers_aroundLimit_needsServiceAsExpected(int kilometers, boolean expected) {
        truck.addKilometers(kilometers);
        assertEquals(expected, truck.needsService());
    }

    @Test
    void performService_afterKilometers_resetsCounter() {
        truck.addKilometers(10001);
        truck.performService();
        assertEquals(0, truck.getKilometersSinceService());
        assertFalse(truck.needsService());
    }

    @ParameterizedTest
    @ValueSource(ints = { -100, 0})
    void constructor_negativeCapacity_throws(int noValidCapacity) {
        assertThrows(IllegalArgumentException.class, () -> truckWithCapacity(noValidCapacity));
    }

    @Test
    void addKilometers_multipleCalls_resetsCounter() {
        truck.addKilometers(6000);
        truck.addKilometers(5000);
        assertTrue(truck.needsService());
        assertEquals(11000, truck.getKilometersSinceService());
    }

    @Test
    void newTruck_doesNotNeedService() {
        assertFalse(truck.needsService());
    }

    @Test
    void getCurrentLocation_newTruck_isUnknown() {
        assertEquals("Unknown", truck.getCurrentLocation());
    }

    @Test
    void updateLocation_newValue_isReturned() {
        truck.updateLocation("Queretaro");
        assertEquals("Queretaro", truck.getCurrentLocation());
    }

    private Truck truckWithCapacity(int capacity) {
        return new Truck(ID, BRAND, MODEL, YEAR, BASE_DAILY_RATE, capacity);
    }
}