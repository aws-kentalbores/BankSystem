package mainpackage;

/**
 * Defines the core operations supported by a bank.
 */
public interface Bank {

    /**
     * Adds a new account to the bank.
     *
     * @param account the account to add
     */
    void addAccount(BankAccount account);

    /**
     * Retrieves and displays an account by its ID.
     *
     * @param id the ID of the account to retrieve
     */
    void getAccount(int id);
}
