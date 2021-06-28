package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getCardPower() {
        assertEquals(Card.getCardPower(), 52);
    }

    @Test
    void isValid() {
        Card card = new Card();
        assertFalse(card.isValid(), "card is valid");
    }
}