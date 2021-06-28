package game;

/**
 * Class for describing a card that has a suit, value.
 */
class Card implements Playable {
    /**
     * Possible types of card suit
     */
    protected enum CardSuit {
        DIAMOND('D'), // ♦ // U+2666
        CLUB('C'), // ♣ // U+2663
        HEART('H'), // ♥ // U+2665
        SPADE('S'); // ♠ // U+2660

        private final Character name;

        CardSuit(Character name) {
            this.name =  name;
        }

        public Character getName() {
            return name;
        }
    }

    /**
     * Possible types of card value
     */
    protected enum CardName {
        ACE("A"),
        KING("K"),
        QUEEN("Q"),
        JACK("J"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10");

        public String getName() {
            return  name.length() < 2 ? name + " " : name;
        }

        private final String name;

        CardName(String name) {
            this.name = name;
        }
    }

    private CardSuit suit;
    private CardName name;


    /**
     * Get the number of cards possible with CardSuit, CardName parameters. (The power of the deck)
     * @return the positive number
     */
    static int getCardPower() {
        return CardName.values().length * CardSuit.values().length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                name == card.name;
    }

    @Override
    public void show() {
        System.out.print((suit == null ? "-" : suit.getName()) + (name == null ? "-" : name.getName()));
    }

    @Override
    public boolean isValid() {
        return suit != null && name != null;
    }

    protected void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    protected void setName(CardName name) {
        this.name = name;
    }

    protected CardSuit getSuit() {
        return suit;
    }

    protected CardName getName() {
        return name;
    }
}
