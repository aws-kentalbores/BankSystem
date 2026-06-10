package mainpackage;

import java.util.HashMap;
import java.util.Map;

public class BankAccountManager implements Bank {
	public Map<Integer, BankAccount> accounts;
	private int id;
	
	public BankAccountManager() {
		accounts = new HashMap<>();
		id = 0;
	}

	@Override
	public void addAccount(BankAccount account) {
		if (id >= 100) {
			System.out.println("Maximum account limit reached. Cannot add more accounts.");
			return;
		}
		accounts.put(id, account);
		System.out.println("Account added with ID: " + id);
		id++;

	}

	@Override
	public void getAccount(int id) {
		if (accounts.containsKey(id)) {
			BankAccount account = accounts.get(id);
			System.out.println("Account ID: " + id);
			System.out.println("Balance: " + account.getBalance());
			System.out.println("Is Frozen: " + account.isAccountFrozen());
		} else {
			System.out.println("Account with ID " + id + " does not exist.");
		}

	}
	
	public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        for (Map.Entry<Integer, BankAccount> e : accounts.entrySet()) {
            int accountId = e.getKey();
            BankAccount account = e.getValue();
            System.out.println("Account ID: " + accountId);
            System.out.println("Name: " + ((SavingsAccount) account).getOwnerName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("-------------------------");
        }
    }

}
