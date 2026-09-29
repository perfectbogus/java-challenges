package dev.perfectbogus.system.vehicle.rental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ElectricCarTest {

    private static final String ID = "E-001";
    private static final String BRAND = "E-Brand";
    private static final String MODEL = "E-Model";
    private static final int YEAR = java.time.Year.now().getValue();
    private static final double BASE_DAILY_RATE = 50.0;
    private static final int NUMBER_OF_DOORS = 4;
    private static final int FULL_BATTERY_LEVEL = 100;

    private ElectricCar car;

    @BeforeEach
    void setup() {
        car = new ElectricCar(ID, BRAND, MODEL, YEAR, BASE_DAILY_RATE, NUMBER_OF_DOORS, FULL_BATTERY_LEVEL);
    }

    @Test
    void constructor_batteryLevelZero_succeed() {
        assertDoesNotThrow(() -> electricCarWithBatteryLevel(0));
    }

    @Test
    void charge_negativePercentage_throws() {
        assertThrows(IllegalArgumentException.class, () -> car.charge(-100));
    }

    @Test
    void rent_batteryLevelHigh_succeed() {
        car.rent();
        assertFalse(car.isAvailable());
    }

    @Test
    void rent_batteryLevelLow_throws() {
        ElectricCar minimumBatteryCar = electricCarWithBatteryLevel(19);
        assertThrows(IllegalStateException.class, minimumBatteryCar::rent);
        assertTrue(minimumBatteryCar.isAvailable());
    }

    @Test
    void rent_batteryLevelLimit_succeed() {
        ElectricCar lowLevelBatteryCar = electricCarWithBatteryLevel(20);
        lowLevelBatteryCar.rent();
        assertFalse(lowLevelBatteryCar.isAvailable());
    }

    @Test
    void isFullyCharged_whenBattery100() {
        assertTrue(car.isFullyCharged());
    }

    @Test
    void isFullyCharged_whenBattery99_false() {
        ElectricCar almostFullCar = electricCarWithBatteryLevel(99);
        assertFalse(almostFullCar.isFullyCharged());
    }

    @Test
    void charge_nearFull_capsAt100() {
        ElectricCar almostFullCar = electricCarWithBatteryLevel(95);
        almostFullCar.charge(10);
        assertEquals(100, almostFullCar.getBatteryLevel());
    }

    @Test
    void charge_normalAmount_addToLevel() {
        ElectricCar electricCar = electricCarWithBatteryLevel(50);
        electricCar.charge(10);
        assertEquals(60, electricCar.getBatteryLevel());
    }

    @ParameterizedTest(name = "invalidBatteryLevel: {0}")
    @ValueSource(ints = { -1, 101})
    void constructor_invalidBatteryLevel_throws(int invalidBatteryLevel) {
        assertThrows(IllegalArgumentException.class, () -> new ElectricCar(ID, BRAND, MODEL, YEAR, BASE_DAILY_RATE, NUMBER_OF_DOORS, invalidBatteryLevel));
    }

    @ParameterizedTest(name = "{0} days -> ${1}")
    @CsvSource({
            "6, 360.0",
            "7, 378.0",
            "10, 540.0"
    })
    void calculateRentCost_applyDiscount(int days, double expected) {
        assertEquals(expected, car.calculateRentalCost(days), 0.001);
    }

    @ParameterizedTest(name = "{0}% battery -> {1} km")
    @CsvSource({
            "100, 300",
            "50, 150",
            "0, 0"
    })
    void getRangeKm_withCurrentBatteryLevel(int batteryLevel, int expectedRange) {
        assertEquals(expectedRange, electricCarWithBatteryLevel(batteryLevel).getRangeKm());
    }

    private ElectricCar electricCarWithBatteryLevel(int batteryLevel) {
        return new ElectricCar(ID, BRAND, MODEL, YEAR, BASE_DAILY_RATE, NUMBER_OF_DOORS, batteryLevel);
    }

}