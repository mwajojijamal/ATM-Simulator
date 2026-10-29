package ATM;
import java.util.Scanner;

public class ATMSimulator {
 public static void main(String[]args) {
	 Scanner scanner = new Scanner(System.in);
	 // you will start with initial balance of ksh:1,000
	BankAccount account = new BankAccount(1000.00);
	boolean running = true;
	
	 System.out.println("=====================================================");
	 System.out.println(" Welcome to java virtual ATM");
	 System.out.println("=====================================================");
	 
	 while(running) {
		 System.out.println("\nSelect an option:");
		 System.out.println("1. Check Balance");
		 System.out.println("2. Deposit Funds");
		 System.out.println("3. Withdraw Cash");
		 System.out.println("4. exit");
		 System.out.println("Enter Choice (1-4):");
		 
		 if(!scanner.hasNextInt()) {
			 System.out.println("Invalid input. please enter a nummber between 1 aand 4.");
			 scanner.next(); //cllears out non interger input
			 continue;
		 }
		 int choice = scanner.nextInt();
		 switch (choice) {
		 case 1:
			 System.out.printf("Current balance: $%.2f%n", account.getbalance());
			 break;
			   
		 case 2:
			 System.out.print("Enter deposit amount: $");
			 if(scanner.hasNextDouble()) {
				
					 double depositAmount = scanner.nextDouble();
					 if(account.deposit(depositAmount)) {
						 System.out.printf("Deposited: $%.2f%n", depositAmount);
						 System.out.printf("New Balance: $%.2f%n", account.getbalance());
					 }
					 
					 }else {
						System.out.println("invalid amount entered.");
						scanner.next();
					}
			 break;
			 
		 case 3:
			 System.out.print("Enter withdrawal amounr: $");
			 if (scanner.hasNextDouble()) {
				 double withdrawAmount = scanner.nextDouble();
				 if(account.withdraw(withdrawAmount)) {
					 System.out.printf("Dispensed: $%.2f%n", withdrawAmount);
					 System.out.printf("Remaining Balance: $%.2f%n", account.getbalance());
					 
					  }
			 } else {
				 System.out.println("Invalid amount entered.");
				 scanner.next();
			 }
			 break;
			 
		 case 4:
			 System.out.println("Thank you for banking with us. Goodbye!");
			 running = false;
			 break;
			 
			 default:
				 System.out.println("invalid option. please choose a valid menu item.");
				 
			 
			 
			 
			 
			 
				 
		 }
	 }
	 scanner.close();
	 
 }
}
