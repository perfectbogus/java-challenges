package dev.perfectbogus.abstraction;

import java.util.List;

public class AbstractClassesIntermediateChallenge {

    // ==========================================================
    // Shapes — used by CHALLENGE 1, CHALLENGE 2 and CHALLENGE 9.
    // ==========================================================
    public abstract static class Shape {
        public abstract double area();

        // CHALLENGE 1
        // Returns a description of the form "Area: X.XX", where X.XX is
        // area() formatted to exactly two decimal places (String.format
        // with "%.2f").
        public String describe() {
            return String.format("Area: %.2f", area());
        }
    }

    // Fully implemented — nothing to do here.
    public static class Circle extends Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    // Fully implemented — nothing to do here.
    public static class Rectangle extends Shape {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }
    }

    public static class Square extends Shape {
        private final double side;

        public Square(double side) {
            this.side = side;
        }

        // CHALLENGE 2
        // Returns the area of the square (side * side).
        @Override
        public double area() {
            return side * side;
        }
    }

    // ==========================================================
    // Vehicles — used by CHALLENGE 3.
    // ==========================================================
    public abstract static class Vehicle {
        protected final String name;

        protected Vehicle(String name) {
            this.name = name;
        }

        public abstract int maxSpeed();

        // Fully implemented — nothing to do here.
        public String describe() {
            return name + " tops out at " + maxSpeed() + " km/h";
        }
    }

    public static class Car extends Vehicle {
        private final int topSpeed;

        // CHALLENGE 3
        // Passes name up to the Vehicle constructor and stores topSpeed
        // in this object's own field.
        public Car(String name, int topSpeed) {
            super(name);
            this.topSpeed = topSpeed;
        }

        @Override
        public int maxSpeed() {
            return topSpeed;
        }
    }

    // ==========================================================
    // Employees — used by CHALLENGE 4.
    // ==========================================================
    public abstract static class Employee {
        protected final double baseSalary;

        protected Employee(double baseSalary) {
            this.baseSalary = baseSalary;
        }

        public abstract String title();

        // Fully implemented — nothing to do here.
        public double bonus() {
            return baseSalary * 0.1;
        }
    }

    public static class Manager extends Employee {
        public Manager(double baseSalary) {
            super(baseSalary);
        }

        @Override
        public String title() {
            return "Manager";
        }

        // CHALLENGE 4
        // Overrides bonus() to return whatever Employee's own bonus()
        // would have returned, plus a flat 500 on top.
        @Override
        public double bonus() {
            return super.bonus() + 500;
        }
    }

    // ==========================================================
    // Media players — used by CHALLENGE 5.
    // ==========================================================
    public interface Playable {
        void play();
        void pause();
    }

    public abstract static class MediaPlayer implements Playable {
        protected final List<String> log;

        protected MediaPlayer(List<String> log) {
            this.log = log;
        }

        // Fully implemented — nothing to do here.
        @Override
        public void pause() {
            log.add("paused");
        }
    }

    public static class AudioPlayer extends MediaPlayer {
        public AudioPlayer(List<String> log) {
            super(log);
        }

        // CHALLENGE 5
        // Appends "playing audio" to the shared log.
        @Override
        public void play() {
            super.log.add("playing audio");
        }
    }

    // ==========================================================
    // Reports — used by CHALLENGE 6.
    // ==========================================================
    public abstract static class Report {
        protected abstract String header();
        protected abstract String body();
        protected abstract String footer();

        // CHALLENGE 6
        // Combines header(), body() and footer(), in that order, into a
        // single String, with each part separated by a newline character
        // ("\n"). This method is final: every subclass shares this exact
        // assembly logic and can only customize the three parts.
        public final String generate() {
            return header() + "\n" +body() + "\n" + footer();
        }
    }

    // Fully implemented — nothing to do here.
    public static class InvoiceReport extends Report {
        @Override
        protected String header() {
            return "INVOICE";
        }

        @Override
        protected String body() {
            return "1 item - $10";
        }

        @Override
        protected String footer() {
            return "Thank you";
        }
    }

    // ==========================================================
    // Entities — used by CHALLENGE 7.
    // ==========================================================
    public abstract static class Entity {
        private static int nextId = 1;

        protected final int id;

        // Fully implemented — nothing to do here. Every subclass shares
        // this single counter, however many different subclasses exist.
        protected Entity() {
            this.id = nextId++;
        }

        public abstract String type();

        // Fully implemented — nothing to do here.
        public String label() {
            return "#" + id + " (" + type() + ")";
        }
    }

    // Fully implemented — nothing to do here.
    public static class Product extends Entity {
        @Override
        public String type() {
            return "Product";
        }
    }

    public static class User extends Entity {
        // CHALLENGE 7
        // Returns "User".
        @Override
        public String type() {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    // ==========================================================
    // Priced items — used by CHALLENGE 8.
    // ==========================================================
    public abstract static class Priced {
        public abstract double price();

        // CHALLENGE 8
        // Returns true if this item's price() is strictly greater than
        // other's price(), false otherwise (including when they're
        // equal). Works for any two Priced items, regardless of their
        // concrete types.
        public boolean isMoreExpensiveThan(Priced other) {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    // Fully implemented — nothing to do here.
    public static class Book extends Priced {
        private final double p;

        public Book(double p) {
            this.p = p;
        }

        @Override
        public double price() {
            return p;
        }
    }

    // Fully implemented — nothing to do here.
    public static class Gadget extends Priced {
        private final double p;

        public Gadget(double p) {
            this.p = p;
        }

        @Override
        public double price() {
            return p;
        }
    }

    // ==========================================================
    // Bank accounts — used by CHALLENGE 10.
    // ==========================================================
    public abstract static class BankAccountBase {
        protected double balance;

        // Fully implemented — nothing to do here. This validation runs
        // for every subclass, even though BankAccountBase itself can
        // never be instantiated directly.
        protected BankAccountBase(double initialBalance) {
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative");
            }
            this.balance = initialBalance;
        }

        public abstract double interestRate();

        public double getBalance() {
            return balance;
        }

        // CHALLENGE 10
        // Adds balance * interestRate() to balance, and returns the new
        // balance.
        public double applyInterest() {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    // Fully implemented — nothing to do here.
    public static class SavingsAccount extends BankAccountBase {
        public SavingsAccount(double initialBalance) {
            super(initialBalance);
        }

        @Override
        public double interestRate() {
            return 0.05;
        }
    }

    // ==========================================================
    // CHALLENGE 9
    // Returns the sum of area() over every shape in shapes (0 for an
    // empty list), without needing to know the concrete type of any
    // individual element.
    // ==========================================================
    public static double totalArea(List<Shape> shapes) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
