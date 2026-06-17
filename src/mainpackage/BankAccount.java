package mainpackage;

import java.util.ArrayList;
import exception.InvalidAmountException;
import exception.AccountFrozenException;
import exception.InsufficientFundsException;
import java.util.List;

public class BankAccount {
    /**
     * The balance of the bank account.
     */
    private double balance;

    /**
     * Indicates whether the bank account is frozen.
     */
    private boolean isFrozen;

    /**
     * A list to store the transaction history of the bank account.
     */
    private List<Transaction> transactions;

    /**
     * Constructs a new BankAccount with an initial balance of 0 and an empty
     * transaction history.
     */
    public BankAccount() {
        balance = 0.0;
        isFrozen = false;
        transactions = new ArrayList<>();
    }

    /**
     * Deposits a specified amount into the bank account.
     *
     * @param amount
     * @throws InvalidAmountException
     * @throws AccountFrozenException
     * @throws InsufficientFundsException
     */
    public void deposit(final double amount) throws InvalidAmountException,
            AccountFrozenException, InsufficientFundsException {
        if (isFrozen) {
            System.out
                    .println("Account is frozen. Cannot perform transactions.");
            throw new AccountFrozenException(
                    "Account is frozen. " + "Cannot perform transactions.");
        }
        if (amount <= 0) {
            System.out.println("Invalid amount. Must be greater than 0.");
            throw new InvalidAmountException(
                    "Invalid amount. " + "Must be greater than 0.");
        }
        balance += amount;

        Transaction transaction = new Transaction("Deposit", amount);
        transactions.add(transaction);

    }

    /**
     * Withdraws a specified amount from the bank account.
     *
     * @param amount the amount to be withdrawn
     * @throws InvalidAmountException     if the withdrawal amount is not
     *                                    positive
     * @throws AccountFrozenException     if the account is frozen
     * @throws InsufficientFundsException if there are insufficient funds in the
     *                                    account
     */
    public void withdraw(final double amount) throws InvalidAmountException,
            AccountFrozenException, InsufficientFundsException {
        if (isFrozen) {
            System.out.println(
                    "Account is frozen. " + "Cannot perform transactions.");
            throw new AccountFrozenException(
                    "Account is frozen." + " Cannot perform transactions.");
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            throw new InsufficientFundsException("Insufficient funds.");
        }
        if (amount <= 0) {
            System.out.println("Withdrawal amount " + "must be positive.");
            throw new InvalidAmountException(
                    "Withdrawal " + "amount must be positive.");
        }
        balance -= amount;

        Transaction transaction = new Transaction("Withdrawal", amount);
        transactions.add(transaction);
    }

    /**
     * Retrieves the current balance of the bank account.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Freezes the bank account, preventing any transactions from being
     * performed.
     */
    public void freezeAccount() {
        isFrozen = true;
    }

    /**
     * Unfreezes the bank account, allowing transactions to be performed again.
     */
    public void unfreezeAccount() {
        isFrozen = false;
    }

    /**
     * Checks if the bank account is currently frozen.
     * 
     * @return true if the account is frozen, false otherwise
     */
    public boolean isAccountFrozen() {
        return isFrozen;
    }

    /**
     * Retrieves the transaction history of the bank account.
     *
     * @return a list of transactions associated with the account
     */
    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

}
