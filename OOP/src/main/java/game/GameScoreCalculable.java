package game;

/**
 * Implementing this interface allows an object to be calculated
 * @param <T> any object
 */
public interface GameScoreCalculable<T> {
    /**
     * Get object points
     * @param item any object
     * @return score
     * @throws Exception if something wrong happened
     */
    int calculateScore(T item) throws Exception;
}
