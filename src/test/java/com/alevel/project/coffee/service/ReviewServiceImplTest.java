package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.repository.ReviewRepo;
import com.alevel.project.coffee.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReviewServiceImplTest {
    private ReviewRepo reviewRepo = mock(ReviewRepo.class);
    private ReviewServiceImpl reviewService = new ReviewServiceImpl(reviewRepo);

    private static final Review review = new Review();
    private static final Place place = new Place();
    private static final Contact contact = new Contact();
    private static final User user = new User();

    @BeforeEach
    void setUp() {
        review.setId(1L);
        review.setText("First review");

        place.setId(1L);
        place.setTitle("CoffeeHouse");
        place.setTimeOpening(0);
        place.setTimeClosing(23);
        contact.setAddress("Kharkov");
        place.setContact(contact);

        user.setUsername("User1");
        user.setEmail("email@email.email");
        user.setPassword("password");
    }

    @Test
    void shouldCreateNewReviewTest() {
        reviewService.createNewReview(place, review, user);
        Mockito.verify(reviewRepo, Mockito.times(1)).save(review);
    }

    @Test
    void shouldUpdateReviewTest() {
        reviewService.update(review, "New review");
        Mockito.verify(reviewRepo, Mockito.times(1)).save(review);
    }

    @Test
    void shouldNotUpdateReviewTest() {
        reviewService.update(review, "First review");
        Mockito.verify(reviewRepo, Mockito.times(1)).save(review);
    }

    @Test
    void shouldDeleteReviewTest() {
        when(reviewRepo.findById(1L)).thenReturn(java.util.Optional.of(review));
        reviewService.deleteReview(review);
        Mockito.verify(reviewRepo, Mockito.times(1)).delete(review);
    }

}
