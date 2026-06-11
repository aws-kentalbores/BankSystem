package mainpackage;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {	
		BankAccountManager manager = new BankAccountManager();
		BankAccount account;
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\nWelcome to the Bank Account Management System!\n [1] Use Account \n [2] Create Account \n [3] List Accounts \n [4] Get Account \n [5] Transaction History of Account \n [6] Exit");
			int choice = sc.nextInt();
			sc.nextLine(); // Consume newline
			if (choice == 1) {
				System.out.println("Enter account ID:");
				int id = sc.nextInt();
				sc.nextLine(); // Consume newline
				if (manager.accounts.containsKey(id)) {
					account = (SavingsAccount) manager.accounts.get(id);
					System.out.println("Hello, " + ((SavingsAccount) account).getOwnerName() + "! Your savings account has been accessed");
					System.out.println("Your current balance is: " + account.getBalance());

					while (true) {
						System.out.println("Enter a command (deposit, withdraw, freeze, unfreeze, logout):");
						String command = sc.nextLine();

						if (command.equalsIgnoreCase("deposit")) {
							System.out.println("Enter amount to deposit:");
							double amount = sc.nextDouble();
							sc.nextLine(); // Consume newline
							account.deposit(roundToTwoDecimalPlaces(amount));
							System.out.println("Current balance: " + account.getBalance());
						} else if (command.equalsIgnoreCase("withdraw")) {
							System.out.println("Enter amount to withdraw:");
							double amount = sc.nextDouble();
							sc.nextLine(); // Consume newline
							account.withdraw(roundToTwoDecimalPlaces(amount));
							System.out.println("Current balance: " + account.getBalance());
						} else if (command.equalsIgnoreCase("freeze")) {
							account.freezeAccount();
							System.out.println("Your account has been frozen.");
						} else if (command.equalsIgnoreCase("unfreeze")) {
							account.unfreezeAccount();
							System.out.println("Your account has been unfrozen.");
						} else if (command.equalsIgnoreCase("logout")) {
							System.out.println("Logging out of the account.");
							break;
						} else {
							System.out.println("Invalid command. Please try again.");
						}
					}
				} else {
					System.out.println("Account with ID " + id + " does not exist.");
				}
					
			} else if (choice == 2) {
				System.out.println("Enter your name:");
				String nameInput = sc.nextLine();
				account = new SavingsAccount(nameInput);
				manager.addAccount(account);
			} else if (choice == 3) {
				manager.listAccounts();
			} else if (choice == 4) {
				System.out.println("Enter account ID:");
				int id = sc.nextInt();
				sc.nextLine(); // Consume newline
				manager.getAccount(id);
			} else if (choice == 5) {
				System.out.println("Enter account ID:");
				int id = sc.nextInt();
				sc.nextLine(); // Consume newline
				if (manager.accounts.containsKey(id)) {
					account = (SavingsAccount) manager.accounts.get(id);
					System.out.println("Transaction history for account ID " + id + ":");
					for (Transaction t : account.getTransactionHistory()) {
						System.out.println("-------------------------");
						System.out.println(t);
					}
				} else {
					System.out.println("Account with ID " + id + " does not exist.");
				}
			} else if (choice == 6) {
				System.out.println("Thank you for using the Bank Account Management System. Goodbye!");
				break;
			} else {
				System.out.println("Invalid choice. Please try again.");
			}
			
		}
	}

	public static double roundToTwoDecimalPlaces(double amount) {
		
		return Math.round(amount * 100.0) / 100.0;

	}
}
