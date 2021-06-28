package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculator of point
 */
class BlackjackSolitaireCalculator implements GameScoreCalculable<MainArea<BlackjackCard>> {
    /**
     * Calculate final score
     * @param cards user cards
     * @return The number of points for a given layout of cards
     * @throws BlackjackException if cards is null
     */
    @Override
    public int calculateScore(MainArea<BlackjackCard> cards) throws BlackjackException {
        if (cards == null) throw new BlackjackException("Empty data");
        if (!cards.isFull()) throw new BlackjackException("Not the entire area is filled with values");
        int score = 0;
        try {
            for (List<BlackjackCard> row: cards.getRows()) {
                score += calculateCardsScore(row, false); // rows
            }
            for (List<BlackjackCard> column: cards.getColumns()) {
                score += calculateCardsScore(column, MainArea.isExtremeColumn(column)); // cols
            }
        } catch (NullPointerException e) {
            System.out.println("Error : can't get an intermedia score");
        } catch (BlackjackException e) {
            System.out.println("Error : can't get a score. " + e.getMessage());
        }
        return score;
    }

    /**
     * Calculate points for a given set of cards
     * @param cards the set of cards
     * @param blackjackCase is blackjack possible in this case
     * @return points for a given set of cards
     * @throws NullPointerException if the card doesn't exist
     * @throws BlackjackException if it was not possible to find out the points
     */
    private static int calculateCardsScore(List<BlackjackCard> cards, boolean blackjackCase) throws NullPointerException, BlackjackException {
        if (cards == null) throw new NullPointerException();
        List<Integer> pointVariants = new ArrayList<Integer>(){{add(0);}};
        for (BlackjackCard card : cards) {
            int maxPoint = card.getPoint(BlackjackCard.BlackjackCardPointType.MAX);
            int minPoint = card.getPoint(BlackjackCard.BlackjackCardPointType.MIN);
            if (maxPoint != minPoint) {
                int len = pointVariants.size();
                pointVariants.addAll(new ArrayList<>(pointVariants));
                for (int i = 0; i < pointVariants.size(); i++) {
                    pointVariants.set(i, pointVariants.get(i) + (i < len ? minPoint : maxPoint));
                }
            } else {
                for (int i = 0; i < pointVariants.size(); i++) {
                    pointVariants.set(i, pointVariants.get(i) + maxPoint);
                }
            }
        }
        return pointVariants.stream().map(point -> getScoreFromPoints(point, blackjackCase)).max(Integer::compareTo).orElse(0);
    }

    /**
     * Convert Card Points to Game Points
     * @param points card points
     * @param blackjackCase is blackjack possible in this case
     * @return game point
     */
    private static int getScoreFromPoints(int points, boolean blackjackCase) {
        int score = 0;
        if (blackjackCase && points == 21) score = 10;
        else if (points == 21) score = 7;
        else if (points == 20) score = 5;
        else if (points == 19) score = 4;
        else if (points == 18) score = 3;
        else if (points == 17) score = 2;
        else if (points <= 16) score = 1;
        return score;
    }

}
