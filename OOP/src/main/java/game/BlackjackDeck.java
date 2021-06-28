package game;

/**
 * A deck that "produces" cards for the blackjack game.
 */
class BlackjackDeck extends AbstractDeck {

    /**
     * Create deck and fill it with ids
     */
    BlackjackDeck() {
       super(BlackjackCard.getCardPower());
    }

    /**
     * Extract a card from Deck
     * @return the top card
     * @throws BlackjackException if the card ID is null
     */
    BlackjackCard popCard() throws BlackjackException {
        Integer cardNumber = extractCardId();
        if (cardNumber == null || !BlackjackCard.isValidNumber(cardNumber)) {
            throw new BlackjackException("Unknown card number " + (cardNumber == null ? "" : cardNumber));
        }
        Card.CardSuit suit = BlackjackCard.getSuitByNumber(cardNumber);
        Card.CardName name = BlackjackCard.getNameByNumber(cardNumber);
        return new BlackjackCard(suit, name);
    }

    /**
     * We do not show the contents of the deck
     */
    @Override
    public void show() {
    }

}
