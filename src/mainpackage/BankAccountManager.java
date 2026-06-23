package mainpackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages bank accounts and transaction processing.
 */
public class BankAccountManager implements Bank {

    /**
     * The maximum number of bank accounts that can be managed.
     */
    private static final int MAX_ACCOUNTS = 100;

    /**
     * A map to store bank accounts with their corresponding IDs.
     */
    private final Map<Integer, BankAccount> accounts;

    /**
     * An integer to keep track of the next available account ID.
     */
    private int nextId;

    /**
     * Constructs a new BankAccountManager.
     */
    public BankAccountManager() {
        this.accounts = new HashMap<>();
        this.nextId = 0;
    }

    @Override
    public final void addAccount(final BankAccount account) {
        if (nextId >= MAX_ACCOUNTS) {
            System.out.println("Maximum account limit reached."
                    + " Cannot add more accounts.");
            return;
        }
        accounts.put(nextId, account);
        System.out.println("Account added with ID: " + nextId);
        nextId++;
    }

    @Override
    public final void getAccount(final int id) {
        if (accounts.containsKey(id)) {
            BankAccount account = accounts.get(id);
            System.out.println("Account ID: " + id);
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Is Frozen: " + account.isAccountFrozen());
        } else {
            System.out.println("Account with ID " + id + " does not exist.");
        }
    }

    /**
     * Lists all accounts currently managed.
     */
    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        for (Map.Entry<Integer, BankAccount> entry : accounts.entrySet()) {
            int accountId = entry.getKey();
            BankAccount account = entry.getValue();
            System.out.println("\n-------------------------");
            System.out.println("Account ID: " + accountId);

            // Safer casting check
            if (account instanceof SavingsAccount) {
                System.out.println(
                        "Name: " + ((SavingsAccount) account).getOwnerName());
            }

            System.out.println("Balance: " + account.getBalance());
            System.out.println("-------------------------");
        }
    }

    /**
     * Filters transactions that are at or above a specified amount.
     *
     * @param amount the threshold amount
     * @param transactions the list of transactions to filter
     * @return a list of transactions meeting the criteria
     */
    public List<Transaction> filterTransactionsAtOrAbove(final double amount,
            final List<Transaction> transactions) {
        System.out.println("Filtering transactions at or above "
            + amount + "...");
        List<Transaction> result = transactions.stream()
                .filter(tx -> tx.getAmount() >= amount).toList();
        printTransactions(result);
        return result;
    }

    /**
     * Sorts transactions by their amount in ascending order.
     *
     * @param transactions the list of transactions to sort
     * @return a sorted list of transactions
     */
    public List<Transaction> sortTransactionsByAmount(
            final List<Transaction> transactions) {
        System.out.println("Sorting transactions by amount...");
        List<Transaction> result = transactions.stream()
                .sorted((tx1, tx2) -> Double.compare(tx1.getAmount(),
                        tx2.getAmount()))
                .toList();
        printTransactions(result);
        return result;
    }

    /**
     * Prints a list of transactions, one per line.
     *
     * @param transactions the transactions to print
     */
    private void printTransactions(final List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No matching transactions.");
            return;
        }
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    /**
     * Retrieves the map of bank accounts.
     *
     * @return the map of bank accounts
     */
    @SuppressWarnings("rawtypes")
    public Map getBankAccount() {
        return accounts;
    }
}
