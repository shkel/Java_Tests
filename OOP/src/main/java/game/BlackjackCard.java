package game;

/**
 * Class for describing a card that has a suit, value, ID.
 */
class BlackjackCard extends Card implements Copyable<BlackjackCard> {
    /**
     * The card id
     */
    private final int number;

    /**
     * The types of points a card can have
     */
    enum BlackjackCardPointType {MAX, MIN}

    /**
     * Create a card with properties
     * @param suit given suit
     * @param name given name
     */
    protected BlackjackCard(CardSuit suit, CardName name) {
        setSuit(suit);
        setName(name);
        number = getCardNumber(suit, name);
    }

    /**
     * Transform card properties to card ID
     * @param suit suit of the card
     * @param name name of the card
     * @return the card ID
     */
    private int getCardNumber(CardSuit suit, CardName name) {
        return (suit.ordinal() + 1) * (name.ordinal() + 1);
    }

    /**
     * Create a copy
     * @return new object with the same properties
     */
    @Override
    public BlackjackCard copy() {
        return new BlackjackCard(getSuit(), getName());
    }

    /**
     * Get the card point
     * @param type kind of points (max, min)
     * @return the point of card
     * @throws BlackjackException if the name is not valid
     */
    byte getPoint(BlackjackCardPointType type) {
        int point = -2;
        switch (getName()) {
            case ACE: {
                switch (type) {
                    case MAX: {
                        point = 11;
                        break;
                    }
                    case MIN: {
                        point = 1;
                        break;
                    }
                }
                break;
            }
            case KING:
            case QUEEN:
            case JACK:
            case TEN:
                {point = 10; break;}
            case TWO: {point = 2; break;}
            case THREE: {point = 3; break;}
            case FOUR: {point = 4; break;}
            case FIVE: {point = 5; break;}
            case SIX: {point = 6; break;}
            case SEVEN: {point = 7; break;}
            case EIGHT: {point = 8; break;}
            case NINE: {point = 9; break;}
        };
        return (byte) point;
    }

    @Override
    public boolean isValid() {
        return number > 0 && super.isValid();
    }

    /**
     * Check the validity of the code.
     * @param cardNumber is the card ID
     * @return {@code true} if this ID can be in a card with such properties (of this type)
     */
    static boolean isValidNumber(int cardNumber) {
        return cardNumber > 0 && cardNumber <= getCardPower();
    }

    /**
     * Get card suit by Card ID
     * @param cardId the number of the card in the list
     * @return a card suit
     * @throws BlackjackException if the ID is not valid
     */
    static CardSuit getSuitByNumber(int cardId) throws BlackjackException {
        if (!isValidNumber(cardId)) throw new BlackjackException("Wrong Card ID");
        return getValueFromArray(CardSuit.values(), cardId);
    }

    /**
     * Get card name by Card ID
     * @param cardId the number of the card in the list
     * @return a card name
     * @throws BlackjackException if the ID is not valid
     */
    static CardName getNameByNumber(int cardId) throws BlackjackException {
        if (!isValidNumber(cardId)) throw new BlackjackException("Wrong Card ID");
        return getValueFromArray(CardName.values(), cardId);
    }

    /**
     * Find an object in the array for the current card.
     * @param values the array in which we are looking for an object for the card.
     * @param <T> any value
     * @return the element of array
     * @throws BlackjackException if there is no suitable value for card ID in the array
     */
    private static <T> T getValueFromArray(T[] values, int number) throws BlackjackException{
        int index = (number - 1) % values.length;
        if (index < 0 || index >= values.length) {
            throw new BlackjackException("Can't find the card property");
        }
        return values[index];
    }

    @Override
    public String toString() {
        return "BlackjackCard{" +
                "number=" + number +
                ", suit=" + getSuit()+
                ", name=" + getName()+
                '}';
    }
}
