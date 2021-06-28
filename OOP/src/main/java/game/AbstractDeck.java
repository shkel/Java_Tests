package game;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A deck of cards that keeps keys in random order.
 */
abstract class AbstractDeck implements Playable {
    /**
     * unique card ids
     */
    private final Deque<Integer> availableCards;

    /**
     * Create a deck with initialized elements
     * @param deckSize must be a positive number
     */
    protected AbstractDeck(int deckSize) {
        availableCards = new ArrayDeque<>(deckSize);
        try {
            fillDeck(deckSize);
        } catch (BlackjackException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Extract an element from Deck
     * @return the card ID (number)
     * @throws BlackjackException if the deck is empty
     */
    protected Integer extractCardId() throws BlackjackException {
        if (!isValid() || availableCards.size() < 1) {
            throw new BlackjackException("Deck is empty");
        }
        return availableCards.pop();
    }

    @Override
    public boolean isValid() {
        return availableCards != null;
    }

    /**
     * Fill the deck with IDs in random order.
     * @param deckSize positive number
     * @throws BlackjackException if the deck size is negative
     */
    private void fillDeck(int deckSize) throws BlackjackException {
        if (deckSize < 1) {
            throw new BlackjackException("Wrong deck size : " + deckSize);
        }
        List<Integer> cardNumbers = IntStream.rangeClosed(1, deckSize).boxed().collect(Collectors.toList());
        Collections.shuffle(cardNumbers);
        availableCards.addAll(cardNumbers);
    }
}
