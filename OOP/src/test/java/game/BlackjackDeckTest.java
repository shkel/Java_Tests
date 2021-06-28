package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BlackjackDeckTest {
    private final int deckCapacity = 52;
    BlackjackDeck d = new BlackjackDeck();

    @Test
    void extractCardId() {
        for (int i = 0; i < deckCapacity; i++) {
            assertTrue(() -> {
                Integer id;
                try {
                    id = d.extractCardId();
                } catch (BlackjackException e) {
                    return false;
                }
                return id != null && id > 0 && id <= deckCapacity;
            });
        }

        Exception exception = assertThrows(BlackjackException.class, d::extractCardId);
        assertEquals("Deck is empty", exception.getMessage());
    }

    @Test
    void popCard() {
        for (int i = 0; i < deckCapacity; i++) {
            try {
                assertNotNull(d.popCard());
            } catch (BlackjackException e) {
                assertNotNull(null);
            }
        }
        Exception exception = assertThrows(BlackjackException.class, d::popCard);
        assertEquals("Deck is empty", exception.getMessage());
    }

}