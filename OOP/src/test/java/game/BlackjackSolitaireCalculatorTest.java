package game;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BlackjackSolitaireCalculatorTest {
    MainArea<BlackjackCard> cards = new MainArea<>();
    List<BlackjackCard> values = cards.getSlotValues();
    BlackjackSolitaireCalculator calculator =  new BlackjackSolitaireCalculator();

    @Test
    void calculateScoreExceptions() {
        Exception exception = assertThrows(BlackjackException.class, () -> calculator.calculateScore(null));
        assertEquals("Empty data", exception.getMessage());
        values.set(0, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.JACK));
        exception = assertThrows(BlackjackException.class, () -> calculator.calculateScore(cards));
        assertEquals("Not the entire area is filled with values", exception.getMessage());
    }

    @Test
    void calculateScore1() {
        values.set(0, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.JACK));
        values.set(1, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.NINE));
        values.set(2, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.THREE));
        values.set(3, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.FIVE));
        values.set(4, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.JACK));
        values.set(5, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.ACE));
        values.set(6, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.NINE));
        values.set(7, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FOUR));
        values.set(8, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.SIX));
        values.set(9, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.SIX));
        values.set(10, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.THREE));
        values.set(11, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.EIGHT));
        values.set(12, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.EIGHT));
        values.set(13, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.TWO));
        values.set(14, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.THREE));
        values.set(15, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.ACE));
        assertEquals(24, calculator.calculateScore(cards));
    }

    @Test
    void calculateScore2() {
        values.set(0, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.TEN));
        values.set(1, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.SEVEN));
        values.set(2, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.SEVEN));
        values.set(3, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.SIX));
        values.set(4, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.QUEEN));
        values.set(5, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.ACE));
        values.set(6, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.NINE));
        values.set(7, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FOUR));
        values.set(8, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.TWO));
        values.set(9, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.QUEEN));
        values.set(10, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.KING));
        values.set(11, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.KING));
        values.set(12, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FOUR));
        values.set(13, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.FIVE));
        values.set(14, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.FIVE));
        values.set(15, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.NINE));
        assertEquals(26, calculator.calculateScore(cards));
    }

    @Test
    void calculateScore3() {
        values.set(0, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.KING));
        values.set(1, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.NINE));
        values.set(2, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.EIGHT));
        values.set(3, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.SIX));
        values.set(4, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.ACE));
        values.set(5, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.ACE));
        values.set(6, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FOUR));
        values.set(7, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.NINE));
        values.set(8, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.SIX));
        values.set(9, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.JACK));
        values.set(10, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.SIX));
        values.set(11, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.FOUR));
        values.set(12, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.QUEEN));
        values.set(13, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.EIGHT));
        values.set(14, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.NINE));
        values.set(15, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.THREE));
        assertEquals(30, calculator.calculateScore(cards));
    }

    @Test
    void calculateScore4() {
        values.set(0, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.QUEEN));
        values.set(1, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.TWO));
        values.set(2, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.THREE));
        values.set(3, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.QUEEN));
        values.set(4, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.ACE));
        values.set(5, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.ACE));
        values.set(6, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FOUR));
        values.set(7, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.KING));
        values.set(8, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.SEVEN));
        values.set(9, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.JACK));
        values.set(10, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.SIX));
        values.set(11, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.SEVEN));
        values.set(12, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.EIGHT));
        values.set(13, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.NINE));
        values.set(14, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.NINE));
        values.set(15, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.THREE));
        assertEquals(41, calculator.calculateScore(cards));
    }

    @Test
    void calculateScore5() {
        values.set(0, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.ACE));
        values.set(1, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.NINE));
        values.set(2, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FOUR));
        values.set(3, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.THREE));
        values.set(4, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.TEN));
        values.set(5, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.JACK));
        values.set(6, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.SIX));
        values.set(7, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FIVE));
        values.set(8, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.FOUR));
        values.set(9, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.ACE));
        values.set(10, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.JACK));
        values.set(11, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.TWO));
        values.set(12, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.SIX));
        values.set(13, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.FOUR));
        values.set(14, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.QUEEN));
        values.set(15, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.SEVEN));
        assertEquals(42, calculator.calculateScore(cards));
    }

    @Test
    void calculateScore6() {
        values.set(0, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.TEN));
        values.set(1, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.TWO));
        values.set(2, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FIVE));
        values.set(3, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.THREE));
        values.set(4, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.ACE));
        values.set(5, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.ACE));
        values.set(6, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FOUR));
        values.set(7, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FOUR));
        values.set(8, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.TWO));
        values.set(9, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.TEN));
        values.set(10, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.FIVE));
        values.set(11, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.NINE));
        values.set(12, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.SEVEN));
        values.set(13, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.JACK));
        values.set(14, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.THREE));
        values.set(15, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.EIGHT));
        assertEquals(67, calculator.calculateScore(cards));
    }

    @Test
    void calculateScore7() {
        values.set(0, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.JACK));
        values.set(1, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.FIVE));
        values.set(2, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.KING));
        values.set(3, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.QUEEN));
        values.set(4, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.NINE));
        values.set(5, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.JACK));
        values.set(6, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.TWO));
        values.set(7, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.FOUR));
        values.set(8, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.TWO));
        values.set(9, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.QUEEN));
        values.set(10, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.FOUR));
        values.set(11, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.KING));
        values.set(12, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.TEN));
        values.set(13, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.ACE));
        values.set(14, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.EIGHT));
        values.set(15, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.EIGHT));
        assertEquals(12, calculator.calculateScore(cards));
    }

    @Test
    void calculateScore8() {
        values.set(0, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.QUEEN));
        values.set(1, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.FIVE));
        values.set(2, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.THREE));
        values.set(3, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.TWO));
        values.set(4, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.TEN));
        values.set(5, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.EIGHT));
        values.set(6, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.EIGHT));
        values.set(7, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.SIX));
        values.set(8, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.TEN));
        values.set(9, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.KING));
        values.set(10, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.FOUR));
        values.set(11, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.FIVE));
        values.set(12, new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.EIGHT));
        values.set(13, new BlackjackCard(Card.CardSuit.DIAMOND, Card.CardName.QUEEN));
        values.set(14, new BlackjackCard(Card.CardSuit.HEART, Card.CardName.JACK));
        values.set(15, new BlackjackCard(Card.CardSuit.SPADE, Card.CardName.ACE));
        assertEquals(24, calculator.calculateScore(cards));
    }
}