package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Container with unchanged size.
 * @param <T> element to be stored in the area
 */
class SlotsArea<T> implements Playable {
    /**
     * The list of elements
     */
    private final List<T> slots;

    /**
     * Create area and fill with the values
     * @param capacity the number of elements to be created
     */
    protected SlotsArea(int capacity) {
        slots = new ArrayList<>(capacity);
        fillArea(capacity);
    }

    /**
     * Check if the area is full
     * @return {@code true} if there are no free places in the area
     */
    boolean isFull() {
        return slots != null && slots.stream().noneMatch(Objects::isNull);
    }

    // We're not using copying here, but returning real objects, just to exploit this loophole in tests.

    /**
     * Get a list of values
     * @return the list of elements
     */
    protected List<T> getSlotValues() {
        return slots;
    }

    /**
     * Place an item in the specified slot.
     * @param item element to be placed in the area. (from 0 (inclusive) to `capacity` (exclusive))
     * @param index the number of the slot. positive value
     * @throws BlackjackException if the area is not created or wrong index
     */
    void occupy(T item, int index) throws BlackjackException {
        if (isValid()) {
            if (isValidIndex(index)) {
                slots.set(index, item);
            } else {
                throw new BlackjackException("Invalid slot index");
            }
        } else {
            throw new BlackjackException("Slots Area is empty");
        }
    }

    /**
     * Add an object to the area.
     * @param item element to be placed in the area
     * @throws BlackjackException if you fail to discard the card
     */
    protected void occupy(T item) throws BlackjackException {
        if (isValid()) {
            boolean discarded = false;
            for (int i = 0; i < slots.size(); i++) {
                if (slots.get(i) == null) {
                    slots.set(i, item);
                    discarded = true;
                    break;
                }
            }
            if (!discarded) throw new BlackjackException("Discard area is full");
        } else {
            throw new BlackjackException("Slots Area is empty");
        }
    }

    /**
     * Check the existence of a slot in the area
     * @param index the slot number
     * @return {@code true} if there is slot number
     */
    private boolean isValidIndex(int index) {
        return  index >= 0 && index < slots.size();
    }

    /**
     * Check if the slot is not free.
     * @param index the number of a slot
     * @return {@code true} if there is an object in the slot
     */
    boolean isOccupied(int index) {
        return !isValid() || !isValidIndex(index) || slots.get(index) != null;
    }

    @Override
    public void show() {
        slots.forEach(System.out::println);
    }

    @Override
    public boolean isValid() {
        return slots != null;
    }

    /**
     * Fill the area with null values.
     * @param capacity the number of elements to be created
     */
    private void fillArea(int capacity) {
        for (int i = 0; i < capacity; i++) {
            slots.add(null);
        }
    }
}
