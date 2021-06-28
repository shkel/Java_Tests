package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackCardTest {
    BlackjackCard defCard = new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.ACE);

    @Test
    void testCopy() {
        assertFalse(() -> defCard == defCard.copy());
        assertEquals(defCard, defCard.copy(), "wrong copy");
    }

    @Test
    void equals() {
        assertEquals(new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.ACE), defCard);
    }

    @Test
    void testGetPoint() {
        BlackjackCard card = new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.ACE);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 11);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 1);

        card = new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.KING);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 10);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 10);

        card = new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.QUEEN);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 10);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 10);

        card = new BlackjackCard(Card.CardSuit.HEART, Card.CardName.JACK);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 10);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 10);

        card = new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.TEN);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 10);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 10);

        card = new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.NINE);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 9);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 9);

        card = new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.EIGHT);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 8);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 8);

        card = new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.SEVEN);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 7);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 7);

        card = new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.SIX);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 6);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 6);

        card = new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.FIVE);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 5);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 5);

        card = new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.FOUR);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 4);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 4);

        card = new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.THREE);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 3);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 3);

        card = new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.TWO);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MAX), 2);
        assertEquals(card.getPoint(BlackjackCard.BlackjackCardPointType.MIN), 2);
    }

    @Test
    void getSuitByNumber() {
        Exception exception = assertThrows(BlackjackException.class, () -> BlackjackCard.getSuitByNumber(0));
        assertEquals("Wrong Card ID", exception.getMessage());

        exception = assertThrows(BlackjackException.class, () -> BlackjackCard.getSuitByNumber(BlackjackCard.getCardPower() + 1));
        assertEquals("Wrong Card ID", exception.getMessage());
    }

    @Test
    void getSuitByNumbers() throws BlackjackException {
        for (int i = 0; i < 52; i+= Card.CardSuit.values().length) {
            assertEquals(Card.CardSuit.DIAMOND, BlackjackCard.getSuitByNumber(1 + i));
            assertEquals(Card.CardSuit.CLUB, BlackjackCard.getSuitByNumber(2 + i));
            assertEquals(Card.CardSuit.HEART, BlackjackCard.getSuitByNumber(3 + i));
            assertEquals(Card.CardSuit.SPADE, BlackjackCard.getSuitByNumber(4 + i));
        }
    }
    @Test
    void getNameByNumber() {
        Exception exception = assertThrows(BlackjackException.class, () -> BlackjackCard.getNameByNumber(0));
        assertEquals("Wrong Card ID", exception.getMessage());

        exception = assertThrows(BlackjackException.class, () -> BlackjackCard.getNameByNumber(BlackjackCard.getCardPower() + 1));
        assertEquals("Wrong Card ID", exception.getMessage());
    }

    @Test
    void getNameByNumbers() throws BlackjackException{
        for (int i = 0; i < 52; i+= Card.CardName.values().length) {
            assertEquals(Card.CardName.ACE, BlackjackCard.getNameByNumber(1 + i));
            assertEquals(Card.CardName.KING, BlackjackCard.getNameByNumber(2 + i));
            assertEquals(Card.CardName.QUEEN, BlackjackCard.getNameByNumber(3 + i));
            assertEquals(Card.CardName.JACK, BlackjackCard.getNameByNumber(4 + i));
            assertEquals(Card.CardName.TWO, BlackjackCard.getNameByNumber(5 + i));
            assertEquals(Card.CardName.THREE, BlackjackCard.getNameByNumber(6 + i));
            assertEquals(Card.CardName.FOUR, BlackjackCard.getNameByNumber(7 + i));
            assertEquals(Card.CardName.FIVE, BlackjackCard.getNameByNumber(8 + i));
            assertEquals(Card.CardName.SIX, BlackjackCard.getNameByNumber(9+ i));
            assertEquals(Card.CardName.SEVEN, BlackjackCard.getNameByNumber(10 + i));
            assertEquals(Card.CardName.EIGHT, BlackjackCard.getNameByNumber(11 + i));
            assertEquals(Card.CardName.NINE, BlackjackCard.getNameByNumber(12 + i));
            assertEquals(Card.CardName.TEN, BlackjackCard.getNameByNumber(13 + i));
        }
    }

    @Test
    void getCardPower() {
        assertEquals(52, BlackjackCard.getCardPower());
    }
}