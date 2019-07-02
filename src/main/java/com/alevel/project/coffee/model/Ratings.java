package com.alevel.project.coffee.model;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ratingFoodQuality")
    private int ratingFoodQuality;
    @Column(name = "ratingServiceQuality")
    private int ratingServiceQuality;
    @Column(name = "ratingPriceQuality")
    private int ratingPriceQuality;
    @Column(name = "ratingAtmosphere")
    private int ratingAtmosphere;

    public Ratings() {
    }

    public Ratings(int ratingFoodQuality, int ratingServiceQuality, int ratingPriceQuality, int ratingAtmosphere) {
        this.ratingFoodQuality = ratingFoodQuality;
        this.ratingServiceQuality = ratingServiceQuality;
        this.ratingPriceQuality = ratingPriceQuality;
        this.ratingAtmosphere = ratingAtmosphere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRatingFoodQuality() {
        return ratingFoodQuality;
    }

    public void setRatingFoodQuality(int ratingFoodQuality) {
        this.ratingFoodQuality = ratingFoodQuality;
    }

    public int getRatingServiceQuality() {
        return ratingServiceQuality;
    }

    public void setRatingServiceQuality(int ratingServiceQuality) {
        this.ratingServiceQuality = ratingServiceQuality;
    }

    public int getRatingPriceQuality() {
        return ratingPriceQuality;
    }

    public void setRatingPriceQuality(int ratingPriceQuality) {
        this.ratingPriceQuality = ratingPriceQuality;
    }

    public int getRatingAtmosphere() {
        return ratingAtmosphere;
    }

    public void setRatingAtmosphere(int ratingAtmosphere) {
        this.ratingAtmosphere = ratingAtmosphere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ratings ratings = (Ratings) o;
        return id == ratings.id &&
                ratingFoodQuality == ratings.ratingFoodQuality &&
                ratingServiceQuality == ratings.ratingServiceQuality &&
                ratingPriceQuality == ratings.ratingPriceQuality &&
                ratingAtmosphere == ratings.ratingAtmosphere;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ratingFoodQuality, ratingServiceQuality, ratingPriceQuality, ratingAtmosphere);
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "id=" + id +
                ", ratingFoodQuality=" + ratingFoodQuality +
                ", ratingServiceQuality=" + ratingServiceQuality +
                ", ratingPriceQuality=" + ratingPriceQuality +
                ", ratingAtmosphere=" + ratingAtmosphere +
                '}';
    }
}
