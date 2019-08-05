package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlacesRepo extends JpaRepository<Place, Long> {

    List<Place> findAll();
}
