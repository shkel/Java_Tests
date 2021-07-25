package dal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class IsinHistory {
    private @Id @GeneratedValue Long id;
    private String isin;
    private double bid;
    private double asc;

    IsinHistory() {
    }

    public IsinHistory(String isin, double bid, double asc) {
        this.isin = isin;
        this.bid = bid;
        this.asc = asc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsc() {
        return asc;
    }

    public void setAsc(double asc) {
        this.asc = asc;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IsinHistory that = (IsinHistory) o;
        return Double.compare(that.bid, bid) == 0 &&
                Double.compare(that.asc, asc) == 0 &&
                id.equals(that.id) &&
                isin.equals(that.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isin, bid, asc);
    }
}
