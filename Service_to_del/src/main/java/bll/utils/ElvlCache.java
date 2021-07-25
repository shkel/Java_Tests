package bll.utils;

import bll.entities.Isin;

import java.util.concurrent.ConcurrentHashMap;

// TODO: spring aspect ?

public class ElvlCache {
    // TODO: initialize form db/service
    private static final ConcurrentHashMap<String, Double> elvls = new ConcurrentHashMap<>(); // cache

    public ElvlCache() {
    }

    public Double updateElvl(Isin isin) {
        if (isin == null || isin.getIsin() == null) {
            throw new NullPointerException("isin is not initialized");
        }
        return elvls.compute(isin.getIsin(), (key, value) -> ElvlCalculator.calculateElvl(value, isin.getBid(), isin.getAsk()));
    }

    public Double getElvl(String isin) {
        return elvls.get(isin);
    }
}
