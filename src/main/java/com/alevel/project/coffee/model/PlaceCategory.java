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
    private Long id;

    @Column(name = "place_category")
    private String placeCategory;

    @ManyToMany
    @JoinTable(name = "coffeeHouse_placeCategory",
            joinColumns = @JoinColumn(name = "place_category_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_house_id"))
    private Set<CoffeeHouse> coffeeHouses;

    public PlaceCategory() {
    }

    public PlaceCategory(String placeCategory) {
        this.placeCategory = placeCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceCategory() {
        return placeCategory;
    }

    public void setPlaceCategory(String placeCategory) {
        this.placeCategory = placeCategory;
    }

    public Set<CoffeeHouse> getCoffeeHouses() {
        return coffeeHouses;
    }

    public void setCoffeeHouses(Set<CoffeeHouse> coffeeHouses) {
        this.coffeeHouses = coffeeHouses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceCategory that = (PlaceCategory) o;
        return id == that.id &&
                Objects.equals(placeCategory, that.placeCategory) &&
                Objects.equals(coffeeHouses, that.coffeeHouses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placeCategory, coffeeHouses);
    }

    @Override
    public String toString() {
        return "PlaceCategory{" +
                "id=" + id +
                ", placeCategory='" + placeCategory + '\'' +
                ", coffeeHouses=" + coffeeHouses +
                '}';
    }
}
