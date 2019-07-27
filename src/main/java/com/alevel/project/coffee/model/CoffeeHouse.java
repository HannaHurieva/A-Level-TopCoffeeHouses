package com.alevel.project.coffee.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "coffee_houses")
public class CoffeeHouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coffee_house_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "timeOpening")
    private int timeOpening;

    @Column(name = "timeClosing")
    private int timeClosing;

    @OneToOne(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Contact contact;

    @OneToOne(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Rating rating;

    @OneToMany(mappedBy = "coffeeHouse", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "coffeeHouse_cuisineType",
            joinColumns = @JoinColumn(name = "coffee_house_id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_type_id"))
    private Set<CuisineType> cuisineTypes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "coffeeHouse_placeCategory",
            joinColumns = @JoinColumn(name = "coffee_house_id"),
            inverseJoinColumns = @JoinColumn(name = "place_category_id"))
    private Set<PlaceCategory> placeCategories;

    public CoffeeHouse() {
    }

    public CoffeeHouse(String title, String description,
                       int timeOpening, int timeClosing, Contact contact) {
        this.title = title;
        this.description = description;
        this.timeOpening = timeOpening;
        this.timeClosing = timeClosing;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeOpening() {
        return timeOpening;
    }

    public void setTimeOpening(int timeOpening) {
        this.timeOpening = timeOpening;
    }

    public int getTimeClosing() {
        return timeClosing;
    }

    public void setTimeClosing(int timeClosing) {
        this.timeClosing = timeClosing;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<CuisineType> getCuisineTypes() {
        return cuisineTypes;
    }

    public void setCuisineTypes(Set<CuisineType> cuisineTypes) {
        this.cuisineTypes = cuisineTypes;
    }

    public Set<PlaceCategory> getPlaceCategories() {
        return placeCategories;
    }

    public void setPlaceCategories(Set<PlaceCategory> placeCategories) {
        this.placeCategories = placeCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeHouse that = (CoffeeHouse) o;

        if (timeOpening != that.timeOpening) return false;
        if (timeClosing != that.timeClosing) return false;
        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (cuisineTypes != null ? !cuisineTypes.equals(that.cuisineTypes) : that.cuisineTypes != null) return false;
        return placeCategories != null ? placeCategories.equals(that.placeCategories) : that.placeCategories == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + timeOpening;
        result = 31 * result + timeClosing;
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (cuisineTypes != null ? cuisineTypes.hashCode() : 0);
        result = 31 * result + (placeCategories != null ? placeCategories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CoffeeHouse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timeOpening=" + timeOpening +
                ", timeClosing=" + timeClosing +
                ", contact=" + contact +
                ", rating=" + rating +
                ", reviews=" + reviews +
                ", cuisineTypes=" + cuisineTypes +
                ", placeCategories=" + placeCategories +
                '}';
    }
}
