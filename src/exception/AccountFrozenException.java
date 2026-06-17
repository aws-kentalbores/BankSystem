package exception;

public class AccountFrozenException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constructor for AccountFrozenException.
     *
     * @param message the message to be associated with the exception
     */
    private String message;

    /**
     * Constructs an AccountFrozenException with the specified message.
     *
     * @param theMessage the message to be associated with the exception
     */
    public AccountFrozenException(final String theMessage) {
        this.message = theMessage;
    }

    /**
     * Retrieves the message associated with this exception.
     *
     * @return the message of the exception
     */
    public String getMessage() {
        return message;
    }

}
