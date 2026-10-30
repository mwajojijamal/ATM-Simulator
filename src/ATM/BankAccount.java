package ATM;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private final String pin;
    private boolean isLocked;
    private int failedAttempts;
    private static final int MAX_ATTEMPTS = 3;

    // For transaction history
    private final List<Transaction> transactionHistory;

    public BankAccount(double initialBalance, String pin) {

        if (!Double.isFinite(initialBalance)) {
            throw new IllegalArgumentException("Initial balance must be a finite number.");
        }

        if (pin == null || !pin.matches("\\d{4}")) {
            throw new IllegalArgumentException("PIN must contain exactly four digits.");
        }

        this.balance = Math.max(initialBalance, 0.0);
        this.pin = pin;
        this.isLocked = false;
        this.failedAttempts = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean isLocked() {
        return isLocked;
    }

    public int getRemainingAttempts() {
        return Math.max(0, MAX_ATTEMPTS - failedAttempts);
    }

    public boolean authenticate(String enteredPin) {

        if (isLocked) {
            System.out.println("This account is permanently locked due to multiple failed attempts.");
            return false;
        }

        if (this.pin.equals(enteredPin)) {
            failedAttempts = 0;
            return true;
        } else {
            failedAttempts++;

            if (failedAttempts >= MAX_ATTEMPTS) {
                isLocked = true;
                System.out.println("ALERT: 3 incorrect attempts entered. Account has been LOCKED.");
            } else {
                System.out.printf("Incorrect PIN. Attempts remaining: %d%n",
                        getRemainingAttempts());
            }

            return false;
        }
    }

    // Balance getter
    public double getBalance() {
        return balance;
    }

    // Deposit logic
    public boolean deposit(double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Deposit amount must be a valid number greater than zero.");
            return false;
        }

        balance += amount;

        // Record transaction
        transactionHistory.add(new Transaction("DEPOSIT", amount, balance));

        return true;
    }

    // Withdrawal logic
    public boolean withdraw(double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Withdrawal amount must be a valid number greater than zero.");
            return false;
        }

        if (amount > balance) {
            System.out.printf("Insufficient funds. Current balance: KSh %.2f%n", balance);
            return false;
        }

        balance -= amount;

        // Record transaction
        transactionHistory.add(new Transaction("WITHDRAW", amount, balance));

        return true;
    }

    // Mini-statement
    public void printMiniStatement() {

        System.out.println("\n----------- Mini-Statement (Recent Transactions) -----------");

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
            System.out.println("------------------------------------------------------------");
            return;
        }

        System.out.println("Date & Time         | Type       | Amount          | Balance");
        System.out.println("--------------------+------------+-----------------+---------");

        // Shows last 5 transactions
        int startIndex = Math.max(0, transactionHistory.size() - 5);

        for (int i = startIndex; i < transactionHistory.size(); i++) {
            System.out.println(transactionHistory.get(i).getFormattedDetails());
        }

        System.out.println("------------------------------------------------------------");
    }
}