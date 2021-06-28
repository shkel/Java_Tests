package game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainAreaTest {

    MainArea<BlackjackCard> area = new MainArea<>();

    @Test
    void getRowsEmpty() {
        List<List<BlackjackCard>> rows = area.getRows();
        assertNotNull(rows);
        assertEquals( 4, rows.size(), " list");
        for (int i = 0; i < 4; i++) {
            List<BlackjackCard> row = rows.get(i);
            assertNotNull(row);
            assertEquals( 0, row.size(), " row "+ i);
        }
    }

    @Test
    void getRows() {
        BlackjackCard card = new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.KING);
        area.getSlotValues().set(1, card);
        List<List<BlackjackCard>> rows = area.getRows();
        List<BlackjackCard> row = rows.get(0);
        assertEquals(1, row.size());
        assertEquals(card, row.get(0));
    }

    @Test
    void getColumnsEmpty() {
        List<List<BlackjackCard>> cols = area.getColumns();
        assertNotNull(cols);
        assertEquals( 5, cols.size(), " list");
        for (int i = 0; i < 5; i++) {
            List<BlackjackCard> col = cols.get(i);
            assertNotNull(col);
            assertEquals( i == 0 || i == 4 ? 2 : 4, col.size(), " col "+ i);
            for (BlackjackCard val : col) {
                assertNull(val);
            }
        }
    }

    @Test
    void getColumns() {
        BlackjackCard card = new BlackjackCard(Card.CardSuit.CLUB, Card.CardName.KING);
        area.getSlotValues().set(1, card);
        List<List<BlackjackCard>> cols = area.getColumns();
        List<BlackjackCard> col = cols.get(1);
        assertEquals(4, col.size());
        assertEquals(card, col.get(0));
    }

    @Test
    void isExtremeColumn() {
        assertFalse(MainArea.isExtremeColumn(null));
        assertTrue(MainArea.isExtremeColumn(Arrays.asList(1,2,3,4,5)));
        assertFalse(MainArea.isExtremeColumn(Arrays.asList(1,2,3,4)));
        assertTrue(MainArea.isExtremeColumn(Arrays.asList(1,2,3)));
        assertTrue(MainArea.isExtremeColumn(Arrays.asList(1,2)));
        assertTrue(MainArea.isExtremeColumn(Collections.singletonList(1)));
        assertTrue(MainArea.isExtremeColumn(new ArrayList<>()));
    }
}