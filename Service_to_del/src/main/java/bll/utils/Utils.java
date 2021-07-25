package bll.utils;

import bll.entities.Isin;
import dal.entities.IsinHistory;

public class Utils {

    // TODO: automapper
    public static IsinHistory isin2history(Isin item) {
        IsinHistory history = null;
        if (item != null) {
            history = new IsinHistory(item.getIsin(), item.getBid(), item.getAsk());
        }
        return history;
    }
}
