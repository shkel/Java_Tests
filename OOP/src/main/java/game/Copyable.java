package game;

/**
 * Simple copy
 * @param <T> object to be copied
 */
public interface Copyable<T> {
    /**
     * Copy the object
     * @return copy of the object
     */
    T copy();
}
