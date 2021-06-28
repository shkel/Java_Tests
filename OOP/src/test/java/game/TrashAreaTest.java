package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrashAreaTest {

    @Test
    void testDiscard() {
        TrashArea area = new TrashArea();
        int capacity = 4;
        for (int i = 0; i < capacity; i++) {
            area.discard(new Card());
        }

        Exception exception = assertThrows(BlackjackException.class, () -> area.discard(new Card()));
        assertEquals("Discard area is full", exception.getMessage());
    }
}