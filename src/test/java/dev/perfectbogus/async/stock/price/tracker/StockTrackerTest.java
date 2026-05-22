package dev.perfectbogus.async.stock.price.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class StockTrackerTest {

    private StockTracker tracker;

    @BeforeEach
    void setup() {
        tracker = new StockTracker();
    }

    @Test
    void testUpdateAndGetPrice() {
        tracker.updatePrice("APPL", 150.0).join();
        assertEquals(150.0, tracker.getPrice("APPL"), 0.01);
    }

    @Test
    void testUnknownSymbolReturnZero() {
        assertEquals(0.0, tracker.getPrice("UNKNOWN"), 0.01);
    }

    @Test
    void testPriceHistory() {
        tracker.updatePrice("APPL", 150.0).join();
        tracker.updatePrice("APPL", 155.0).join();
        tracker.updatePrice("APPL", 148.0).join();
        assertEquals(List.of(150.0, 155.0, 148.0), tracker.getPriceHistory("APPL"));
    }

    @Test
    void testAlertFiresAboveThreshold() {
        AtomicBoolean fired = new AtomicBoolean(false);
        tracker.addAlert("APPL", 155.0, AlertDirection.ABOVE,
                () -> fired.set(true));
        tracker.updatePrice("APPL", 160.0).join();
        assertTrue(fired.get());
    }

    @Test
    void testAlertFiresBelowThreshold() {
        AtomicBoolean fired = new AtomicBoolean(false);
        tracker.addAlert("APPL", 145.0, AlertDirection.BELOW,
                () -> fired.set(true));
        tracker.updatePrice("APPL", 140.0).join();
        assertTrue(fired.get());
    }

}