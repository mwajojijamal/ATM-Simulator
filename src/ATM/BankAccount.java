package ATM;

public class BankAccount {
private double balance;

public BankAccount(double initialBalance) {
	if(initialBalance >= 0) {
		this.balance = initialBalance;
	} else {
		this.balance = 0.0;
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
