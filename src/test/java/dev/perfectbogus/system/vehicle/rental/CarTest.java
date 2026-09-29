package dev.perfectbogus.system.vehicle.rental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    final String id = "C-001";
    private Car car;

    @BeforeEach
    void setup() {
        car = new Car(id, "Toyota", "Corolla", 2022, 40.0, 4);
    }

    @Test
    @DisplayName("7+ day rental gets 10% of discount")
    void calculateRentalCost_sevenDays_appliesDiscount() {
        double cost = car.calculateRentalCost(7);
        // Assert (40 + 10 insurance) * 7 = 350, then minus 10% = 315
        assertEquals(315.0, cost, 0.001);
    }

    @Test
    void rent_whenAlreadyRent_throws() {
        final String id = "C-001";
        Car car = new Car(id, "Toyota", "Corolla", 2022, 40.0, 4);
        car.rent();

        IllegalStateException ex = assertThrows(IllegalStateException.class, car::rent);
        assertTrue(ex.getMessage().contains(id));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -30})
    void calculateRentalCost_nonPositiveDays_throws(int days) {
        Motorcycle moto = new Motorcycle("M-001", "Honda", "CB500", 2021, 30.0, false);
        assertThrows(IllegalArgumentException.class, () -> moto.calculateRentalCost(days));
    }

}