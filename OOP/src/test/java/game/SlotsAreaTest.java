package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotsAreaTest {

    SlotsArea<Boolean> area = new SlotsArea<>(3);

    @Test
    void isFull() {
        assertFalse(area.isFull());

        area.occupy(true, 0);
        area.occupy(true, 1);
        area.occupy(true, 2);

        assertTrue(area.isFull());
    }

    @Test
    void occupy() {
        Exception exception = assertThrows(BlackjackException.class, () -> area.occupy(true, -1));
        assertEquals("Invalid slot index", exception.getMessage());
        exception = assertThrows(BlackjackException.class, () -> area.occupy(true, 3));
        assertEquals("Invalid slot index", exception.getMessage());
        exception = assertThrows(BlackjackException.class, () -> area.occupy(true, 4));
        assertEquals("Invalid slot index", exception.getMessage());
    }

    @Test
    void isOccupied() {
        area.occupy(false, 1);
        assertTrue(area.isOccupied(-1));
        assertFalse(area.isOccupied(0));
        assertTrue(area.isOccupied(1));
        assertFalse(area.isOccupied(2));
        assertTrue(area.isOccupied(3));
        assertTrue(area.isOccupied(4));
    }
}