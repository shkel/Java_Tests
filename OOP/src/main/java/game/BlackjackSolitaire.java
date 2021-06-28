package game;

import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Console card game
 */
class BlackjackSolitaire {
    private final BlackjackDeck deck = new BlackjackDeck();
    private final TrashArea discards = new TrashArea();
    private final MainArea<BlackjackCard> userCards = new MainArea<>();
    private final GameScoreCalculable<MainArea<BlackjackCard>> calculator = new BlackjackSolitaireCalculator();

    /**
     * Actions that can be performed by the user
     */
    private enum BlackjackSolitaireAction {
        DISCARD,
        SHOW_DISCARDS,
        SHOW_USER_CARDS,
        PUT;
    }

    /**
     * Launches the game
     */
    public void play() {
        System.out.print("Hello. It is the console game.");
        try {
            fillTable();
            System.out.printf(
                    "\nIt was a good game! Your score is %d points.",
                    calculator.calculateScore(userCards)
            );
        }  catch (BlackjackException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lay out the cards on the board
     * @throws BlackjackException if an invalid character was entered
     */
    private void fillTable() throws BlackjackException{
        try (Scanner reader = new Scanner(new InputStreamReader(System.in))) {
            boolean isUsedCard;
            BlackjackCard card;
            while (!userCards.isFull()) {
                card = deck.popCard();
                showNextCard(card);
                isUsedCard = false;
                while (!isUsedCard) {
                    showInvitation();
                    try {
                        String inputValue = reader.nextLine();
                        isUsedCard = useCard(inputValue, card);
                    } catch (NoSuchElementException | IllegalStateException e) {
                        // ctrl + d for example
                        throw new BlackjackException("An invalid character was entered.");
                    }
                }
            }
        }
    }

    private void showInvitation() {
        System.out.printf(
                "Input slot number%s %s: ",
                (!discards.isFull() ? " or 'D' to discard" : ""), "('s' - show discards)"
        );
    }

    private void showNextCard(BlackjackCard card) {
        System.out.println();
        System.out.print("Next card is < ");
        card.show();
        System.out.println(" >");
    }

    /**
     * Manipulate the card.
     * @param inputValue the string entered by the user
     * @param card object with which the actions are performed
     * @return @code true} if the action completed successfully
     */
    private boolean useCard(String inputValue, final BlackjackCard card) {
        boolean isUsedCard = false;
        switch(parseAction(inputValue)) {
            case DISCARD: {
                isUsedCard = discard(card);
                break;
            }
            case SHOW_DISCARDS: {
                discards.show();
                System.out.println();
                break;
            }
            case SHOW_USER_CARDS: {
                userCards.show();
                System.out.println();
                break;
            }
            case PUT: {
                try {
                    isUsedCard = occupy(card, Integer.parseInt(inputValue) - 1);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong number");
                }
                break;
            }
        }
        return isUsedCard;
    }

    /**
     * Convert string to command
     * @param inputValue the string entered by the user
     * @return the action that the user entered
     */
    private BlackjackSolitaireAction parseAction(String inputValue) {
        switch (inputValue) {
            case "D":
            case "d":
                return BlackjackSolitaireAction.DISCARD;
            case "S":
            case "s":
                return BlackjackSolitaireAction.SHOW_DISCARDS;
            case "U":
            case "u":
                return BlackjackSolitaireAction.SHOW_USER_CARDS;
            default:
                return BlackjackSolitaireAction.PUT;
        }
    }

     /**
     * Put a card to the discarded card area
     * @param card the object to be discarded
     * @return {@code true} if the action completed successfully
     */
    private boolean discard(BlackjackCard card) {
        boolean isDiscarded = false;
        if (discards.isFull()) {
            System.out.println("You can't discard the card.");
        } else {
            try {
                discards.discard(card);
                discards.show();
                isDiscarded = true;
            } catch (BlackjackException e) {
                e.printStackTrace();
            }
        }
        return isDiscarded;
    }

    /**
     * Put a card to the playing (user card) area
     * @param card object with which the actions are performed
     * @param slotNumber spot in the area where the card is placed
     * @return {@code true} if the action completed successfully
     */
    private boolean occupy(BlackjackCard card, int slotNumber) {
        boolean isOccupied = false;
        if (userCards.isOccupied(slotNumber)) {
            System.out.println("Slot is occupied");
        } else {
            try {
                userCards.occupy(card, slotNumber);
                userCards.show();
                isOccupied = true;
            } catch (BlackjackException e) {
                System.out.println("The area is not initialized. " + e.getMessage());
            }
        }
        return isOccupied;
    }

}
