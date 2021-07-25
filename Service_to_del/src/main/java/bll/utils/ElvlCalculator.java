package bll.utils;

public class ElvlCalculator {

    public static double calculateElvl(Double lastElvl, Double bid, double ask) {
        if (lastElvl == null && bid == null) {
            return ask;
        } else if (lastElvl == null || bid > lastElvl) {
            return bid;
        } else if (ask < lastElvl) {
            return ask;
        } else {
            return lastElvl;
        }
    }
}
