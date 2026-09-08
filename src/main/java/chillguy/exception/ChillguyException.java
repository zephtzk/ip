package chillguy.exception;

/**
 * Represents invalid user input that the chatbot can explain and recover from.
 */
public class ChillguyException extends Exception {
    /**
     * Creates an error with a message explaining how the user can correct the input.
     *
     * @param message Explanation to display in the console.
     */
    public ChillguyException(String message) {
        super(message);
    }
}
