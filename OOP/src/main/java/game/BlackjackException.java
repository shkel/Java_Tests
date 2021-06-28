package game;

/**
 * The class of exceptions thrown in the game
 */
class BlackjackException extends RuntimeException {

    /**
     * Constructs a new exception with a message.
     * @param errorMessage is the text of error
     */
    public BlackjackException(String errorMessage) {
        super(errorMessage);
    }
}