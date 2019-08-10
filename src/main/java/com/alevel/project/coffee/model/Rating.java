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
    private Long id;

    @Column(name = "ratingFoodQuality")
    private int ratingFoodQuality;

    @Column(name = "ratingServiceQuality")
    private int ratingServiceQuality;

    @Column(name = "ratingPriceQuality")
    private int ratingPriceQuality;

    @Column(name = "ratingAtmosphere")
    private int ratingAtmosphere;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_place_id", referencedColumnName = "place_id")
    private Place place;

    public Rating() {
    }

    public Rating(int ratingFoodQuality, int ratingServiceQuality,
                  int ratingPriceQuality, int ratingAtmosphere,
                  Place place) {
        this.ratingFoodQuality = ratingFoodQuality;
        this.ratingServiceQuality = ratingServiceQuality;
        this.ratingPriceQuality = ratingPriceQuality;
        this.ratingAtmosphere = ratingAtmosphere;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        place.setRating(this);
        this.place = place;
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
                Objects.equals(place, rating.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ratingFoodQuality, ratingServiceQuality, ratingPriceQuality, ratingAtmosphere, place);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", ratingFoodQuality=" + ratingFoodQuality +
                ", ratingServiceQuality=" + ratingServiceQuality +
                ", ratingPriceQuality=" + ratingPriceQuality +
                ", ratingAtmosphere=" + ratingAtmosphere +
                ", place=" + place +
                '}';
    }
}
