package com.alevel.project.coffee.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "reviews", catalog = "db_coffee_in")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @NotBlank(message = "Please write your review")
    @Length(max = 2048, message = "Too much symbols")
    private String text;
    @Length(max = 255, message = "It's too long")
    private String tag;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public Review() {
    }

    public Review(String text, String tag, User author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }

    public Review(String text, String tag){
        this.text = text;
        this.tag = tag;
    }

    @Column(name = "rating_foog_quality" )
    private int ratingFood;

    @Column(name = "rating_service_quality")
    private int ratingService;

    @Column(name = "rating_price_quality")
    private int ratingPrice;

    @Column (name = "rating_atmosphere")
    private int ratingAtmosphere;



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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getRatingFood() {
        return ratingFood;
    }

    public void setRatingFood(int ratingFood) {
        this.ratingFood = ratingFood;
    }

    public int getRatingService() {
        return ratingService;
    }

    public void setRatingService(int ratingService) {
        this.ratingService = ratingService;
    }

    public int getRatingPrice() {
        return ratingPrice;
    }

    public void setRatingPrice(int ratingPrice) {
        this.ratingPrice = ratingPrice;
    }

    public int getRatingAtmosphere() {
        return ratingAtmosphere;
    }

    public void setRatingAtmosphere(int ratingAtmosphere) {
        this.ratingAtmosphere = ratingAtmosphere;
    }
}
