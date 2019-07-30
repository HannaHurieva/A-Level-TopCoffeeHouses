package com.alevel.project.coffee.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @NotBlank(message = "Please write your review")
    @Length(max = 2048, message = "Too much symbols")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;



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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id &&
                Objects.equals(text, review.text) &&
                Objects.equals(author, review.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, author);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author=" + author +
                '}';
    }
}

