package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Party {
    @Id
    int id;
    String name;
    Integer pricePreSaleInEur;
    Integer priceInEur;
    String extraInfo;
    Date date;
    Date doors;

    private Party() {

    }

    public Party(int id, String name, Integer pricePreSaleInEur, Integer priceInEur, String extraInfo, Date date, Date doors) {
        this.id = id;
        this.name = name;
        this.pricePreSaleInEur = pricePreSaleInEur;
        this.priceInEur = priceInEur;
        this.extraInfo = extraInfo;
        this.date = date;
        this.doors = doors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPricePreSaleInEur() {
        return pricePreSaleInEur;
    }

    public void setPricePreSaleInEur(Integer pricePreSaleInEur) {
        this.pricePreSaleInEur = pricePreSaleInEur;
    }

    public Integer getPriceInEur() {
        return priceInEur;
    }

    public void setPriceInEur(Integer priceInEur) {
        this.priceInEur = priceInEur;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDoors() {
        return doors;
    }

    public void setDoors(Date doors) {
        this.doors = doors;
    }
}
