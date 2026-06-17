package mainpackage;

import java.time.LocalDateTime;

/**
 * Represents a financial transaction. This class is final to prevent unintended
 * inheritance.
 */
public final class Transaction {

    /** The type of the transaction. */
    private final String type;

    /** The monetary amount of the transaction. */
    private final double amount;

    /** The exact time the transaction was processed. */
    private final LocalDateTime timeStamp;

    /**
     * Constructs a new Transaction.
     *
     * @param theType   the type of transaction
     * @param theAmount the transaction amount
     */
    public Transaction(final String theType, final double theAmount) {
        this.type = theType;
        this.amount = theAmount;
        this.timeStamp = LocalDateTime.now();
    }

    /**
     * Returns a string representation of the transaction. * @return a formatted
     * string containing transaction details.
     */
    @Override
    public String toString() {
        return "Transaction Type: " + type + ", Amount: " + amount + ", Time: "
                + timeStamp;
    }

    /**
     * Retrieves the type of this transaction.
     *
     * @return the type of the transaction
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the amount of this transaction.
     *
     * @return the amount of the transaction
     */
    public double getAmount() {
        return amount;
    }
}
