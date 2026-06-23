package mainpackage;

public class SavingsAccount extends BankAccount {

    /**
     * The name of the account owner.
     */
    private String ownerName;

    /**
     * Constructs a SavingsAccount with the specified account ID and owner name.
     *
     * @param theOwnerName the name of the account owner
     */
    public SavingsAccount(final String theOwnerName) {
        super();
        this.ownerName = theOwnerName;
    }

    /**
     * Retrieves the name of the account owner.
     * @return the name of the account owner
     */
    public String getOwnerName() {
        return ownerName;
    }

}
