package bll.entities;

public class Isin {
    private String isin;
    private Double bid;
    private double ask;

    Isin() {
    }

    public Isin(String isin, Double bid, double asc, double elvl) {
        this.isin = isin;
        this.bid = bid;
        this.ask = asc;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

}
