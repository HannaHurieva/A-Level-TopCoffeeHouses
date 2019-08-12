package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByAuthor(User author);

    List<Review> findByPlace(Place place);
}
