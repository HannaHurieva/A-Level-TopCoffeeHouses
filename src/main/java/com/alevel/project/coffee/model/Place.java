package com.alevel.project.coffee.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "places")
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Column(name = "description")
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    private String description;

    @Column(name = "timeOpening")
    @Min(value = 0, message = "The time can not be negative, must be greater than or equal to 0")
    @Max(value = 23, message = "The time must be less than or equal to 23")
    private int timeOpening;

    @Column(name = "timeClosing")
    @Min(value = 0, message = "The time can not be negative, must be greater than or equal to 0")
    @Max(value = 23, message = "The time must be less than or equal to 23")
    private int timeClosing;

    @OneToOne(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Contact contact;

    @OneToOne(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Rating rating;


    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Review> reviews;

    @ManyToMany
    @JoinTable(name = "place_cuisine_type",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_type_id"))
    private Set<CuisineType> cuisineTypes;

    @ManyToMany
    @JoinTable(name = "place_to_place_category",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "place_category_id"))
    private Set<PlaceCategory> placeCategories;

    public Place() {
    }

    public Place(String title, String description,
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

        Place place = (Place) o;

        if (timeOpening != place.timeOpening) return false;
        if (timeClosing != place.timeClosing) return false;
        if (id != null ? !id.equals(place.id) : place.id != null) return false;
        if (title != null ? !title.equals(place.title) : place.title != null) return false;
        return description != null ? description.equals(place.description) : place.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + timeOpening;
        result = 31 * result + timeClosing;
        return result;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timeOpening=" + timeOpening +
                ", timeClosing=" + timeClosing +
                '}';
    }
}
