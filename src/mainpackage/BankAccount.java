package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
	
	private double balance;
	private boolean isFrozen;
	List<Transaction> transactions;
	
	
	public BankAccount() {
		balance = 0.0;
		isFrozen = false;
	    transactions = new ArrayList<>();
	}
	
	public void deposit(double amount) {
		if (isFrozen) {
			System.out.println("Account is frozen. Cannot perform transactions.");
			return;
		}
		if (amount <= 0) {
			System.out.println("Deposit amount must be positive.");
			return;
		}
		balance += Main.roundToTwoDecimalPlaces(amount);
		
		Transaction transaction = new Transaction("Deposit", amount);
		transactions.add(transaction);
		
		
	}

	public void withdraw(double amount) {
		if (isFrozen) {
			System.out.println("Account is frozen. Cannot perform transactions.");
			return;
		}
		if (amount > balance) {
			System.out.println("Insufficient funds.");
			return;
		}
		if (amount <= 0) {
			System.out.println("Withdrawal amount must be positive.");
			return;
		}
		balance -= amount;
		
		Transaction transaction = new Transaction("Withdrawal", amount);
		transactions.add(transaction);
	}
	
	public double getBalance() {
		return balance;
	}

	public void freezeAccount() {
		isFrozen = true;
	}
	
	public void unfreezeAccount() {
		isFrozen = false;
	}
	
	public boolean isAccountFrozen() {
		return isFrozen;
	}
	
	public List<Transaction> getTransactionHistory() {
        return transactions;
    }
	

}
