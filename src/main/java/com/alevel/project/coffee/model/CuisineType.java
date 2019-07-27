package com.alevel.project.coffee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cuisine_types")
public class CuisineType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuisine_type_id", unique = true, nullable = false)
    private int id;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @ManyToMany
    @JoinTable(name = "coffeeHouse_cuisineType",
            joinColumns = @JoinColumn(name = "cuisine_type_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_house_id"))
    private Set<CoffeeHouse> coffeeHouses;

    public CuisineType() {
    }

    public CuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
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
        CuisineType that = (CuisineType) o;
        return id == that.id &&
                Objects.equals(cuisineType, that.cuisineType) &&
                Objects.equals(coffeeHouses, that.coffeeHouses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cuisineType, coffeeHouses);
    }

    @Override
    public String toString() {
        return "CuisineType{" +
                "id=" + id +
                ", cuisineType='" + cuisineType + '\'' +
                ", coffeeHouses=" + coffeeHouses +
                '}';
    }
}
