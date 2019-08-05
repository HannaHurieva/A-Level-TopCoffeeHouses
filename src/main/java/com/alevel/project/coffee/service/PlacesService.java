package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.CuisineType;
import com.alevel.project.coffee.model.Place;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlacesService {

    Iterable<Place> findAll();
    Place findByName(String placeName);
    List<Place> findByLocation(String region);
    List<Place> findByCuisineType(CuisineType type);
    List<Place> findByType(String type);



}
