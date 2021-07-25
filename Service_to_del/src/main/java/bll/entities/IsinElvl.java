package bll.entities;

public class IsinElvl {
    private String isin;
    private double elvl;

    IsinElvl() {
    }

    public IsinElvl(String isin, double elvl) {
        this.isin = isin;
        this.elvl = elvl;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public double getElvl() {
        return elvl;
    }

    public void setElvl(double elvl) {
        this.elvl = elvl;
    }
}
