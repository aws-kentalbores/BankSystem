package exception;

public class InsufficientFundsException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 2544689330699869239L;
    /**
     * The detail message for the exception.
     */
    private String message;
    /**
     * Constructs a new InsufficientFundsException with the specified detail
     * message.
     *
     * @param theMessage the message to be associated with the exception
     */
    public InsufficientFundsException(final String theMessage) {
        this.message = theMessage;
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string of this exception
     */
    public String getMessage() {
        return message;
    }


}
