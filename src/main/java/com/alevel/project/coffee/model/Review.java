package com.alevel.project.coffee.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", unique = true, nullable = false)
    private long id;

    @NotBlank(message = "Please write your review")
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_place_id", referencedColumnName = "place_id")
    private Place place;

    @CreatedDate
    @Column(name = "created")
    private Date created;


  /*  @LastModifiedDate
    @Column(name = "updated")
    private Date updated;*/


    public Review() {
    }

    public Review(String text, User author) {
        this.text = text;
        this.author = author;

    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    public String getCreated() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.y");
        return dateFormat.format(created);
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id &&
                Objects.equals(text, review.text) &&
                Objects.equals(author, review.author) &&
                Objects.equals(place, review.place) &&
                Objects.equals(created, review.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, author, place, created);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", place=" + place +
                ", created=" + created +
                '}';
    }
}

