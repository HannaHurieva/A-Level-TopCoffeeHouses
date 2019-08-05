package com.alevel.project.coffee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "place_categories")
public class PlaceCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_category_id", unique = true, nullable = false)
    private int id;

    @Column(name = "place_category")
    private String placeCategory;

    @ManyToMany
    @JoinTable(name = "coffeeHouse_placeCategory",
            joinColumns = @JoinColumn(name = "place_category_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_house_id"))
    private Set<Place> places;

    public PlaceCategory() {
    }

    public PlaceCategory(String placeCategory) {
        this.placeCategory = placeCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceCategory() {
        return placeCategory;
    }

    public void setPlaceCategory(String placeCategory) {
        this.placeCategory = placeCategory;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceCategory that = (PlaceCategory) o;
        return id == that.id &&
                Objects.equals(placeCategory, that.placeCategory) &&
                Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placeCategory, places);
    }

    @Override
    public String toString() {
        return "PlaceCategory{" +
                "id=" + id +
                ", placeCategory='" + placeCategory + '\'' +
                ", places=" + places +
                '}';
    }
}
