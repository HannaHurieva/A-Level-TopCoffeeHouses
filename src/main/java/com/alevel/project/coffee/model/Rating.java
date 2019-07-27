package com.alevel.project.coffee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ratings")
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", unique = true, nullable = false)
    private int id;

    @Column(name = "ratingFoodQuality")
    private int ratingFoodQuality;

    @Column(name = "ratingServiceQuality")
    private int ratingServiceQuality;

    @Column(name = "ratingPriceQuality")
    private int ratingPriceQuality;

    @Column(name = "ratingAtmosphere")
    private int ratingAtmosphere;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_coffee_house_id", referencedColumnName = "coffee_house_id")
    private CoffeeHouse coffeeHouse;

    public Rating() {
    }

    public Rating(int ratingFoodQuality, int ratingServiceQuality,
                  int ratingPriceQuality, int ratingAtmosphere,
                  CoffeeHouse coffeeHouse) {
        this.ratingFoodQuality = ratingFoodQuality;
        this.ratingServiceQuality = ratingServiceQuality;
        this.ratingPriceQuality = ratingPriceQuality;
        this.ratingAtmosphere = ratingAtmosphere;
        this.coffeeHouse = coffeeHouse;
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

    public CoffeeHouse getCoffeeHouse() {
        return coffeeHouse;
    }

    public void setCoffeeHouse(CoffeeHouse coffeeHouse) {
        coffeeHouse.setRating(this);
        this.coffeeHouse = coffeeHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return id == rating.id &&
                ratingFoodQuality == rating.ratingFoodQuality &&
                ratingServiceQuality == rating.ratingServiceQuality &&
                ratingPriceQuality == rating.ratingPriceQuality &&
                ratingAtmosphere == rating.ratingAtmosphere &&
                Objects.equals(coffeeHouse, rating.coffeeHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ratingFoodQuality, ratingServiceQuality, ratingPriceQuality, ratingAtmosphere, coffeeHouse);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", ratingFoodQuality=" + ratingFoodQuality +
                ", ratingServiceQuality=" + ratingServiceQuality +
                ", ratingPriceQuality=" + ratingPriceQuality +
                ", ratingAtmosphere=" + ratingAtmosphere +
                ", coffeeHouse=" + coffeeHouse +
                '}';
    }
}
