package ATM;

public class BankAccount {
private double balance;
private final String pin;
private boolean isLocked;
private int failedAttempts;
private static final int MAX_ATTEMPTS = 3;



public BankAccount(double initialBalance, String pin) {
	this.balance = Math.max(initialBalance, 0.0);
	this.pin= pin;
	this.isLocked = false;
	this.failedAttempts = 0;
	
}

public boolean isLocked() {
	return isLocked;
}

public int getRemainingAttempts() {
	return Math.max(0, MAX_ATTEMPTS - failedAttempts);
}

public boolean authenticate(String enteredpin) {
	if (isLocked) {
		System.out.println("This account is permanently locked due to multiple failed attempts.");
		return false;
	}
	if (this.pin.equals(enteredpin)) {
		failedAttempts = 0; // resets counter after succefull log in
	    return true;
	} else {
		if(failedAttempts >= MAX_ATTEMPTS) {
			isLocked = true;
			System.out.println("ALERT: 3 incorrect attempts entered. Account has been LOCKED.");
		} else {
		System.out.printf("Incorrect PIN. Attempts remaining: %d%n", getRemainingAttempts());
			}
		return false;
	}
}



//balance part
public double getbalance() {
	return balance;
}

//deposit part
public boolean deposit(double amount) {
	if(amount <= 0) {
		System.out.println("Deposit amount must be greater than zero.");
		return false;
	}
	balance += amount;
	return true;
}
//withdrawal part

public boolean withdraw(double amount) {
	if(amount <= 0) {
		System.out.println("withdrawal amount must be greater than zero.");
		return false;
	}
	if(amount > balance) {
		System.out.println("insuffucient funds. current balance : $ " + String.format("%.2f", balance));
		return false;
	}
	balance -= amount;
	return true;
}



}
