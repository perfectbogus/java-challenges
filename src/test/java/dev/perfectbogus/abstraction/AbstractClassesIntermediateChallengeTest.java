package dev.perfectbogus.abstraction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AbstractClassesIntermediateChallengeTest {

    // ==========================================================
    // CHALLENGE 1: Shape.describe()
    // ==========================================================
    @Nested
    class ShapeDescribeTests {

        @Test
        void testRectangleDescribe() {
            AbstractClassesIntermediateChallenge.Rectangle r =
                    new AbstractClassesIntermediateChallenge.Rectangle(4, 5);
            assertEquals("Area: 20.00", r.describe());
        }

        @Test
        void testCircleDescribeRounds() {
            AbstractClassesIntermediateChallenge.Circle c =
                    new AbstractClassesIntermediateChallenge.Circle(2);
            assertEquals("Area: 12.57", c.describe());
        }
    }

    // ==========================================================
    // CHALLENGE 2: Square.area()
    // ==========================================================
    @Nested
    class SquareAreaTests {

        @Test
        void testArea() {
            assertEquals(25.0, new AbstractClassesIntermediateChallenge.Square(5).area());
        }

        @Test
        void testDescribeReusesChallenge1() {
            assertEquals("Area: 25.00", new AbstractClassesIntermediateChallenge.Square(5).describe());
        }
    }

    // ==========================================================
    // CHALLENGE 3: Car constructor
    // ==========================================================
    @Nested
    class CarConstructorTests {

        @Test
        void testMaxSpeed() {
            AbstractClassesIntermediateChallenge.Car car =
                    new AbstractClassesIntermediateChallenge.Car("Civic", 200);
            assertEquals(200, car.maxSpeed());
        }

        @Test
        void testDescribeUsesInheritedNameField() {
            AbstractClassesIntermediateChallenge.Car car =
                    new AbstractClassesIntermediateChallenge.Car("Civic", 200);
            assertEquals("Civic tops out at 200 km/h", car.describe());
        }
    }

    // ==========================================================
    // CHALLENGE 4: Manager.bonus()
    // ==========================================================
    @Nested
    class ManagerBonusTests {

        @Test
        void testBonusIncludesBaseBonusPlusFlatAmount() {
            AbstractClassesIntermediateChallenge.Manager manager =
                    new AbstractClassesIntermediateChallenge.Manager(2000);
            assertEquals(700.0, manager.bonus());
        }

        @Test
        void testDifferentBaseSalary() {
            AbstractClassesIntermediateChallenge.Manager manager =
                    new AbstractClassesIntermediateChallenge.Manager(5000);
            assertEquals(1000.0, manager.bonus());
        }
    }

    // ==========================================================
    // CHALLENGE 5: AudioPlayer.play()
    // ==========================================================
    @Nested
    class AudioPlayerPlayTests {

        @Test
        void testPlayThenPauseLogsInOrder() {
            List<String> log = new ArrayList<>();
            AbstractClassesIntermediateChallenge.AudioPlayer player =
                    new AbstractClassesIntermediateChallenge.AudioPlayer(log);
            player.play();
            player.pause();
            assertEquals(List.of("playing audio", "paused"), log);
        }
    }

    // ==========================================================
    // CHALLENGE 6: Report.generate()
    // ==========================================================
    @Nested
    class ReportGenerateTests {

        @Test
        void testCombinesPartsInOrder() {
            assertEquals(
                    "INVOICE\n1 item - $10\nThank you",
                    new AbstractClassesIntermediateChallenge.InvoiceReport().generate()
            );
        }
    }

    // ==========================================================
    // CHALLENGE 7: User.type()
    // ==========================================================
    @Nested
    class UserTypeTests {

        @Test
        void testTypeAndSharedIdCounter() {
            AbstractClassesIntermediateChallenge.Product p1 = new AbstractClassesIntermediateChallenge.Product();
            AbstractClassesIntermediateChallenge.User u1 = new AbstractClassesIntermediateChallenge.User();
            AbstractClassesIntermediateChallenge.Product p2 = new AbstractClassesIntermediateChallenge.Product();

            assertEquals("User", u1.type());
            // The id counter is shared across every Entity subclass, so
            // ids increase by exactly 1 regardless of which concrete
            // type is instantiated.
            assertEquals(p1.id + 1, u1.id);
            assertEquals(u1.id + 1, p2.id);
            assertEquals("#" + u1.id + " (User)", u1.label());
        }
    }

    // ==========================================================
    // CHALLENGE 8: Priced.isMoreExpensiveThan()
    // ==========================================================
    @Nested
    class IsMoreExpensiveThanTests {

        @Test
        void testStrictlyMoreExpensive() {
            AbstractClassesIntermediateChallenge.Gadget gadget = new AbstractClassesIntermediateChallenge.Gadget(200);
            AbstractClassesIntermediateChallenge.Book book = new AbstractClassesIntermediateChallenge.Book(15);
            assertTrue(gadget.isMoreExpensiveThan(book));
        }

        @Test
        void testReverseComparisonIsFalse() {
            AbstractClassesIntermediateChallenge.Gadget gadget = new AbstractClassesIntermediateChallenge.Gadget(200);
            AbstractClassesIntermediateChallenge.Book book = new AbstractClassesIntermediateChallenge.Book(15);
            assertFalse(book.isMoreExpensiveThan(gadget));
        }

        @Test
        void testEqualPricesIsFalse() {
            AbstractClassesIntermediateChallenge.Book bookA = new AbstractClassesIntermediateChallenge.Book(20);
            AbstractClassesIntermediateChallenge.Gadget gadgetB = new AbstractClassesIntermediateChallenge.Gadget(20);
            assertFalse(bookA.isMoreExpensiveThan(gadgetB));
        }
    }

    // ==========================================================
    // CHALLENGE 9: totalArea
    // ==========================================================
    @Nested
    class TotalAreaTests {

        @Test
        void testMixedShapes() {
            List<AbstractClassesIntermediateChallenge.Shape> shapes = List.of(
                    new AbstractClassesIntermediateChallenge.Rectangle(2, 3),
                    new AbstractClassesIntermediateChallenge.Square(4)
            );
            assertEquals(22.0, AbstractClassesIntermediateChallenge.totalArea(shapes), 0.0001);
        }

        @Test
        void testEmptyList() {
            assertEquals(0.0, AbstractClassesIntermediateChallenge.totalArea(List.of()), 0.0001);
        }

        @Test
        void testIncludesCircle() {
            List<AbstractClassesIntermediateChallenge.Shape> shapes = List.of(
                    new AbstractClassesIntermediateChallenge.Circle(1)
            );
            assertEquals(Math.PI, AbstractClassesIntermediateChallenge.totalArea(shapes), 0.0001);
        }
    }

    // ==========================================================
    // CHALLENGE 10: BankAccountBase.applyInterest()
    // ==========================================================
    @Nested
    class ApplyInterestTests {

        @Test
        void testInterestAppliedAndBalanceUpdated() {
            AbstractClassesIntermediateChallenge.SavingsAccount account =
                    new AbstractClassesIntermediateChallenge.SavingsAccount(1000);
            double result = account.applyInterest();
            assertEquals(1050.0, result, 0.0001);
            assertEquals(1050.0, account.getBalance(), 0.0001);
        }

        @Test
        void testConstructorValidationStillApplies() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new AbstractClassesIntermediateChallenge.SavingsAccount(-10)
            );
        }
    }
}