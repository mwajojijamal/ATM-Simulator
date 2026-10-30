package ATM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final String type; // "DEPOSIT" or "WITHDRAW"
    private final double amount;
    private final double remainingBalance;
    private final LocalDateTime timestamp;

    // Reusable date formatter
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Transaction(String type, double amount, double remainingBalance) {
        this.type = type;
        this.amount = amount;
        this.remainingBalance = remainingBalance;
        this.timestamp = LocalDateTime.now();
    }

    public String getFormattedDetails() {

        String prefix = type.equalsIgnoreCase("DEPOSIT") ? "+KSh " : "-KSh ";

        return String.format("%-19s | %-10s | %-15s | KSh %.2f",
                timestamp.format(FORMATTER),
                type,
                String.format("%s%.2f", prefix, amount),
                remainingBalance);
    }
}