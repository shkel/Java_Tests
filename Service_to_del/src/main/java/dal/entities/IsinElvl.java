package dal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class IsinElvl {
    private @Id @GeneratedValue Long id;
    private String isin;
    private double elvl;

    IsinElvl() {
    }

    public IsinElvl(String isin, double elvl) {
        this.isin = isin;
        this.elvl = elvl;
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

    public double getElvl() {
        return elvl;
    }

    public void setElvl(double elvl) {
        this.elvl = elvl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IsinElvl isinElvl = (IsinElvl) o;
        return Double.compare(isinElvl.elvl, elvl) == 0 &&
                id.equals(isinElvl.id) &&
                isin.equals(isinElvl.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isin, elvl);
    }
}
