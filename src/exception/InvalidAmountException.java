package exception;

/**
 * Thrown to indicate that an invalid monetary amount was supplied,
 * such as a negative or zero deposit or withdrawal amount.
 */
public class InvalidAmountException extends Exception {

    /**
     * Serialization version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidAmountException with the specified message.
     *
     * @param theMessage the message to be associated with the exception
     */
    public InvalidAmountException(final String theMessage) {
        super(theMessage);
    }
}
