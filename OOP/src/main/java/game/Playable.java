package game;

/**
 * Implementing this interface allows an object to be shown for user
 */
interface Playable {
    /**
     * Display the item
     */
    void show();

    /**
     * Check the object for valid.
     *  @return the sign of whether the object data is correct.
     */
    boolean isValid();

}
