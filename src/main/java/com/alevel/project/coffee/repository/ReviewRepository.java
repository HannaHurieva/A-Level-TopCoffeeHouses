package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {

    List<Review> findAll();


}
