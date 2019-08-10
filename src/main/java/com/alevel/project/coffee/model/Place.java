package com.alevel.project.coffee.model;

import com.alevel.project.coffee.model.enums.CuisineTypeEnum;
import com.alevel.project.coffee.model.enums.PlaceCategoryEnum;
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

    @ElementCollection(targetClass = CuisineTypeEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "place_cuisineType", joinColumns = @JoinColumn(name = "fk_place_id"))
    @Enumerated(EnumType.ORDINAL)
    private Set<CuisineTypeEnum> cuisineTypes;

    @ElementCollection(targetClass = PlaceCategoryEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "place_placeCategory", joinColumns = @JoinColumn(name = "fk_place_id"))
    @Enumerated(EnumType.ORDINAL)
    private Set<PlaceCategoryEnum> placeCategories;

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

    public Set<CuisineTypeEnum> getCuisineTypes() {
        return cuisineTypes;
    }

    public void setCuisineTypes(Set<CuisineTypeEnum> cuisineTypes) {
        this.cuisineTypes = cuisineTypes;
    }

    public Set<PlaceCategoryEnum> getPlaceCategories() {
        return placeCategories;
    }

    public void setPlaceCategories(Set<PlaceCategoryEnum> placeCategories) {
        this.placeCategories = placeCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place that = (Place) o;

        if (timeOpening != that.timeOpening) return false;
        if (timeClosing != that.timeClosing) return false;
        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (reviews != null ? !reviews.equals(that.reviews) : that.reviews != null) return false;
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
        result = 31 * result + (cuisineTypes != null ? cuisineTypes.hashCode() : 0);
        result = 31 * result + (placeCategories != null ? placeCategories.hashCode() : 0);
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
                ", contact=" + contact +
                ", rating=" + rating +
                ", reviews=" + reviews +
                ", cuisineTypes=" + cuisineTypes +
                ", placeCategories=" + placeCategories +
                '}';
    }
}
