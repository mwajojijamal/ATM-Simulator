package ATM;

import java.util.Scanner;

public class ATMSimulator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Start with an initial balance of KSh 1,000 and default PIN "1234"
        BankAccount account = new BankAccount(1000.00, "1234");

        boolean running = true;

        System.out.println("=====================================================");
        System.out.println("            Welcome to Java Virtual ATM              ");
        System.out.println("=====================================================");

        // --- Authentication Gate ---
        boolean authenticated = false;

        while (!authenticated && !account.isLocked()) {

            System.out.print("Enter your four-digit PIN: ");
            String enteredPin = scanner.next();

            if (account.authenticate(enteredPin)) {
                authenticated = true;
                System.out.println("Login successful!\n");
            }
        }

        // Exits if account becomes locked
        if (account.isLocked()) {
            System.out.println("Session terminated. Contact support to unlock account.");
            scanner.close();
            return;
        }

        // --- Banking Operations Menu ---
        while (running) {

            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Mini-Statement");
            System.out.println("5. Exit");
            System.out.print("Enter Choice (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.printf("Current Balance: KSh %.2f%n",
                            account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter deposit amount (KSh): ");

                    if (scanner.hasNextDouble()) {
                        double depositAmount = scanner.nextDouble();

                        if (account.deposit(depositAmount)) {
                            System.out.printf("Deposited: KSh %.2f%n", depositAmount);
                            System.out.printf("New Balance: KSh %.2f%n",
                                    account.getBalance());
                        }
                    } else {
                        System.out.println("Invalid amount entered.");
                        scanner.next();
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount (KSh): ");

                    if (scanner.hasNextDouble()) {
                        double withdrawAmount = scanner.nextDouble();

                        if (account.withdraw(withdrawAmount)) {
                            System.out.printf("Dispensed: KSh %.2f%n", withdrawAmount);
                            System.out.printf("Remaining Balance: KSh %.2f%n",
                                    account.getBalance());
                        }
                    } else {
                        System.out.println("Invalid amount entered.");
                        scanner.next();
                    }
                    break;

                case 4:
                    account.printMiniStatement();
                    break;

                case 5:
                    System.out.println("Thank you for banking with us. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose a valid menu item (1-5).");
            }
        }

        scanner.close();
    }
}