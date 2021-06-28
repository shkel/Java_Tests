package game;

import java.util.List;

/**
 * A stack of discarded cards.
 * Capacity is 4.
 */
class TrashArea extends SlotsArea<Playable> {

    TrashArea() {
        super(4);
    }

    /**
     * Add an object to the area.
     * @param item element to be placed in the area
     * @throws BlackjackException if you fail to discard the card
     */
    void discard(Playable item) throws BlackjackException {
        occupy(item);
    }


    @Override
    public void show() {
        List<Playable> slots = getSlotValues();
        for (int i = 0; i < slots.size(); i++) {
            Playable obj = slots.get(i);
            if (i == 0) {
                if (obj == null) {
                    System.out.print("--");
                } else {
                    obj.show();
                }
            } else if ((i % 2) == 0) {
                System.out.println();
                if (obj == null) {
                    System.out.print("--");
                } else {
                    obj.show();
                }
            } else {
                System.out.print(" ");
                if (obj == null) {
                    System.out.print("--");
                } else {
                    obj.show();
                }
            }
        }
    }

}
