package exception;

public class InvalidAmountException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constructor for InvalidAmountException.
     *
     * @param message the message to be associated with the exception
     */
    private String message;

    /**
     * Constructs an InvalidAmountException with the specified message.
     *
     * @param theMessage the message to be associated with the exception
     */
    public InvalidAmountException(final String theMessage) {
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
