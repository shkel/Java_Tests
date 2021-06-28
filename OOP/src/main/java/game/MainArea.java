package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A stack of user cards.
 * Capacity is 16.
 * @param <T> the element that can be placed in an area.
 */
class MainArea<T extends Playable & Copyable<T>> extends SlotsArea<T> {
    private static final byte rowNumber = 4;
    private static final byte columnNumber = 5;
    private static final byte columnNumberNarrow = 3;
    /**
     * the two-dimensional array. row(column)
     */
    private final List<List<Byte>> layout = new ArrayList<>(rowNumber);

    MainArea() {
        super(16);
        initLayout();
    }

    /**
     * Fill the area with null elements.
     */
    private void initLayout() {
        byte i = 0;
        List<T> slots = getSlotValues();
        while (slots != null && i < slots.size()) {
            List<Byte> row = new ArrayList<>(columnNumber);
            boolean isWideRow = i < 2 * columnNumber;
            byte rowLength = isWideRow ? columnNumber : columnNumberNarrow;
            if (!isWideRow) row.add(null);
            for (byte j = i; j < (i + rowLength); j++) row.add(j);
            if (!isWideRow) row.add(null);
            layout.add(row);
            i += rowLength;
        }
    }

    /**
     * Get an array of rows.
     * @return the filtered matrix of elements
     */
    private List<List<T>> getRows(Predicate<? super T> filterFn) {
        List<T> slots = getSlotValues();
        return slots == null ? new ArrayList<>() : layout.stream()
                .map(
                        objectIndexes -> objectIndexes == null
                                ? null
                                : objectIndexes.stream()
                                    .filter(Objects::nonNull)
                                    .map(ind -> {
                                        T value = slots.get(ind);
                                        return value != null ? value.copy() : null;
                                    })
                                    //.filter(Objects::nonNull)
                                    .filter(filterFn)
                                    .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    /**
     * Get an array of rows.
     * @return the matrix of non-empty elements.
     */
    List<List<T>> getRows() {
        return getRows(Objects::nonNull);
    }


    /**
     * Get an array of columns.
     * @return  the matrix of non-empty elements.
     */
    List<List<T>> getColumns() {
        List<T> slots = getSlotValues();
        if (slots == null) return new ArrayList<>();
        List<List<T>> columns = new ArrayList<>(columnNumber);
        for (int columnIndex = 0; columnIndex < columnNumber; columnIndex++) {
            List<T> column = new ArrayList<>(columnNumberNarrow);
            for (List<Byte> row : layout) {
                if (row != null && columnIndex < row.size()) {
                    Byte objectIndex = row.get(columnIndex);
                    if (objectIndex != null && objectIndex >= 0 && objectIndex < slots.size()) {
                        T value = slots.get(objectIndex);
                        column.add(value != null ? value.copy() : null);
                    }
                }
            }
            columns.add(column);
        }
        return columns;
    }

    /**
     * Find out if this column is on the edge of the area.
     * @param column the list of elements
     * @return {@code true} if it is an extreme column
     */
    static boolean isExtremeColumn(List<?> column) {
        return column != null && column.size() != rowNumber;
    }

    @Override
    public void show() {
        List<List<T>> rows = getRows(t -> true);
        if (rows != null) {
            for (int i = 0; i < rows.size(); i++) {
                List<T> row = rows.get(i);
                if (row != null) {
                    if (row.size() < columnNumber) System.out.print("    ");
                    for (T item : row) {
                        if (item == null) {
                            System.out.print("---");
                        } else {
                            item.show();
                        }
                        System.out.print(" ");
                    }
                    if (i < (rows.size() - 1)) System.out.println();
                }
            }
        }
        System.out.println();
    }

}
