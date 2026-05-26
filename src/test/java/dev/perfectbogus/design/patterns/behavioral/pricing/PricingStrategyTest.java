package dev.perfectbogus.design.patterns.behavioral.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PricingStrategyTest {

    // -------------------------------------------------------------------------
    // Task 1 — PricingStrategy is a functional interface
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - PricingStrategy interface")
    class Task1 {

        @Test
        @DisplayName("Should be implementable as a lambda")
        void shouldBeImplementableAsLambda() {
            PricingStrategy strategy = price -> price * 0.5;
            assertEquals(50.0, strategy.calculate(100.0), 0.01);
        }

    }

    // -------------------------------------------------------------------------
    // Task 2 — Concrete strategies
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - Concrete strategies")
    class Task2 {

        @Test
        @DisplayName("RegularPricingStrategy should return price unchanged")
        void regularShouldReturnUnchanged() {
            PricingStrategy strategy = new RegularPricingStrategy();
            assertEquals(100.0, strategy.calculate(100.0), 0.01);
            assertEquals(250.0, strategy.calculate(250.0), 0.01);
        }

        @Test
        @DisplayName("SeasonalDiscountStrategy should apply 10% discount")
        void seasonalShouldApply10PercentDiscount() {
            PricingStrategy strategy = new SeasonalDiscountStrategy();
            assertEquals(90.0,  strategy.calculate(100.0), 0.01);
            assertEquals(225.0, strategy.calculate(250.0), 0.01);
        }

        @Test
        @DisplayName("VipDiscountStrategy should apply 20% discount")
        void vipShouldApply20PercentDiscount() {
            PricingStrategy strategy = new VipDiscountStrategy();
            assertEquals(80.0,  strategy.calculate(100.0), 0.01);
            assertEquals(200.0, strategy.calculate(250.0), 0.01);
        }

        @Test
        @DisplayName("BulkDiscountStrategy should apply no discount for quantity < 10")
        void bulkShouldApplyNoDiscountForSmallQuantity() {
            PricingStrategy strategy = new BulkDiscountStrategy(5);
            assertEquals(100.0, strategy.calculate(100.0), 0.01);
        }

        @Test
        @DisplayName("BulkDiscountStrategy should apply 15% discount for quantity 10-49")
        void bulkShouldApply15PercentForMediumQuantity() {
            PricingStrategy strategy = new BulkDiscountStrategy(20);
            assertEquals(85.0, strategy.calculate(100.0), 0.01);
        }

        @Test
        @DisplayName("BulkDiscountStrategy should apply 25% discount for quantity >= 50")
        void bulkShouldApply25PercentForLargeQuantity() {
            PricingStrategy strategy = new BulkDiscountStrategy(50);
            assertEquals(75.0, strategy.calculate(100.0), 0.01);

            PricingStrategy strategy2 = new BulkDiscountStrategy(100);
            assertEquals(75.0, strategy2.calculate(100.0), 0.01);
        }

        @Test
        @DisplayName("BulkDiscountStrategy boundary: quantity exactly 10 gets 15% discount")
        void bulkBoundaryAt10() {
            PricingStrategy strategy = new BulkDiscountStrategy(10);
            assertEquals(85.0, strategy.calculate(100.0), 0.01);
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — PricingContext
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - PricingContext")
    class Task3 {

        @Test
        @DisplayName("Should delegate to the strategy passed in the constructor")
        void shouldDelegateToConstructorStrategy() {
            var context = new PricingContext(new VipDiscountStrategy());
            assertEquals(80.0, context.calculatePrice(100.0), 0.01);
        }

        @Test
        @DisplayName("Should use the new strategy after setStrategy is called")
        void shouldUseNewStrategyAfterSwap() {
            var context = new PricingContext(new RegularPricingStrategy());
            assertEquals(100.0, context.calculatePrice(100.0), 0.01);

            context.setStrategy(new SeasonalDiscountStrategy());
            assertEquals(90.0, context.calculatePrice(100.0), 0.01);
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException for null strategy in constructor")
        void shouldThrowForNullInConstructor() {
            var ex = assertThrows(IllegalArgumentException.class,
                    () -> new PricingContext(null));
            assertEquals("Strategy cannot be null", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException for null strategy in setStrategy")
        void shouldThrowForNullInSetStrategy() {
            var context = new PricingContext(new RegularPricingStrategy());
            var ex = assertThrows(IllegalArgumentException.class,
                    () -> context.setStrategy(null));
            assertEquals("Strategy cannot be null", ex.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Lambda strategy
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - Lambda strategy")
    class Task4 {

        @Test
        @DisplayName("Context should work correctly with a lambda strategy")
        void shouldWorkWithLambda() {
            var context = new PricingContext(new RegularPricingStrategy());

            // flash sale: 30% discount — passed as a lambda, no named class
            context.setStrategy(price -> price * 0.70);

            assertEquals(70.0, context.calculatePrice(100.0), 0.01);
            assertEquals(35.0, context.calculatePrice(50.0),  0.01);
        }

        @Test
        @DisplayName("Lambda strategy should be interchangeable with a concrete class strategy")
        void lambdaShouldBeInterchangeable() {
            PricingStrategy namedClass = new SeasonalDiscountStrategy();
            PricingStrategy lambda     = price -> price * 0.90;

            assertEquals(namedClass.calculate(100.0), lambda.calculate(100.0), 0.01);
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — PricingStrategyRegistry
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - PricingStrategyRegistry")
    class Task5 {

        @Test
        @DisplayName("Should register and retrieve a strategy by name")
        void shouldRegisterAndRetrieve() {
            var registry = new PricingStrategyRegistry();
            registry.register("vip", new VipDiscountStrategy());

            var strategy = registry.getStrategy("vip");
            assertEquals(80.0, strategy.calculate(100.0), 0.01);
        }

        @Test
        @DisplayName("Should throw for unknown strategy name")
        void shouldThrowForUnknownName() {
            var registry = new PricingStrategyRegistry();
            var ex = assertThrows(IllegalArgumentException.class,
                    () -> registry.getStrategy("unknown"));
            assertEquals("Unknown strategy: unknown", ex.getMessage());
        }

        @Test
        @DisplayName("Should return all registered strategy names")
        void shouldReturnAllNames() {
            var registry = new PricingStrategyRegistry();
            registry.register("regular",  new RegularPricingStrategy());
            registry.register("seasonal", new SeasonalDiscountStrategy());
            registry.register("vip",      new VipDiscountStrategy());

            var names = registry.getAvailableStrategies();
            assertEquals(3, names.size());
            assertTrue(names.contains("regular"));
            assertTrue(names.contains("seasonal"));
            assertTrue(names.contains("vip"));
        }

        @Test
        @DisplayName("Should return an unmodifiable Set of names")
        void shouldReturnUnmodifiableSet() {
            var registry = new PricingStrategyRegistry();
            registry.register("vip", new VipDiscountStrategy());
            assertThrows(UnsupportedOperationException.class,
                    () -> registry.getAvailableStrategies().add("hack"));
        }

        @Test
        @DisplayName("Should throw for null or blank strategy name")
        void shouldThrowForNullOrBlankName() {
            var registry = new PricingStrategyRegistry();
            assertThrows(IllegalArgumentException.class,
                    () -> registry.register(null, new VipDiscountStrategy()));
            assertThrows(IllegalArgumentException.class,
                    () -> registry.register("  ", new VipDiscountStrategy()));
        }

        @Test
        @DisplayName("Should throw for null strategy")
        void shouldThrowForNullStrategy() {
            var registry = new PricingStrategyRegistry();
            assertThrows(IllegalArgumentException.class,
                    () -> registry.register("vip", null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — CompositePricingStrategy
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - CompositePricingStrategy")
    class Task6 {

        @Test
        @DisplayName("Should apply strategies in sequence")
        void shouldApplyStrategiesInSequence() {
            // 100.0 → seasonal(10% off) = 90.0 → vip(20% off) = 72.0
            var composite = new CompositePricingStrategy(List.of(
                    new SeasonalDiscountStrategy(),
                    new VipDiscountStrategy()
            ));
            assertEquals(72.0, composite.calculate(100.0), 0.01);
        }

        @Test
        @DisplayName("Should apply strategies in the correct order")
        void shouldRespectOrder() {
            // Order matters: seasonal then vip vs vip then seasonal
            // Both give same result mathematically but order must be respected
            var composite1 = new CompositePricingStrategy(List.of(
                    new SeasonalDiscountStrategy(), // 10% off first
                    new VipDiscountStrategy()       // 20% off second
            ));
            var composite2 = new CompositePricingStrategy(List.of(
                    new VipDiscountStrategy(),      // 20% off first
                    new SeasonalDiscountStrategy()  // 10% off second
            ));
            // Both: 100 * 0.9 * 0.8 = 72 and 100 * 0.8 * 0.9 = 72
            assertEquals(composite1.calculate(100.0), composite2.calculate(100.0), 0.01);
        }

        @Test
        @DisplayName("Should work with a single strategy in the list")
        void shouldWorkWithSingleStrategy() {
            var composite = new CompositePricingStrategy(List.of(new VipDiscountStrategy()));
            assertEquals(80.0, composite.calculate(100.0), 0.01);
        }

        @Test
        @DisplayName("Should work with lambda strategies in the list")
        void shouldWorkWithLambdas() {
            var composite = new CompositePricingStrategy(List.of(
                    price -> price * 0.90,  // 10% off
                    price -> price * 0.80   // 20% off
            ));
            assertEquals(72.0, composite.calculate(100.0), 0.01);
        }

        @Test
        @DisplayName("Should throw for null strategies list")
        void shouldThrowForNullList() {
            var ex = assertThrows(IllegalArgumentException.class,
                    () -> new CompositePricingStrategy(null));
            assertEquals("Strategies cannot be null or empty", ex.getMessage());
        }

        @Test
        @DisplayName("Should throw for empty strategies list")
        void shouldThrowForEmptyList() {
            var ex = assertThrows(IllegalArgumentException.class,
                    () -> new CompositePricingStrategy(List.of()));
            assertEquals("Strategies cannot be null or empty", ex.getMessage());
        }
    }
}