package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Drink {
    @Id
    private int id;
    private String name;
    private boolean light;
    private boolean alcoholic;
    private double price;

    @ManyToMany
    private List<Venue> venue;

    public Drink() {

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

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Venue> getVenue() {
        return venue;
    }

    public void setVenue(List<Venue> venue) {
        this.venue = venue;
    }
}
