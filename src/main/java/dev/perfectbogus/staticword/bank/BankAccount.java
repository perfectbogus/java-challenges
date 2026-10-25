package dev.perfectbogus.staticword.bank;

public class BankAccount {
    private static double interestRate = 0.02;
    private static int nextId = 1;

    private final int id;
    private double balance;

    static {
        System.out.println("Bank system initialized");
    }

    BankAccount(double initialBalance) {
        this.id = nextId++;
        this.balance = initialBalance;
    }

    void applyInterest() {
        balance += balance * interestRate;
    }

    static void setInterestRate(double rate) {
        interestRate = rate;
    }

    public int getId() {
        return id;
    }
}
