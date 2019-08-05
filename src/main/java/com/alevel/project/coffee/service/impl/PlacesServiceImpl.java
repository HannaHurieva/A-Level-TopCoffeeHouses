package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.CuisineType;
import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.repository.PlacesRepo;
import com.alevel.project.coffee.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlacesServiceImpl implements PlacesService {

    @Autowired
    private PlacesRepo placesRepo;

    @Override
    public Iterable<Place> findAll() {
        return placesRepo.findAll();
    }

    @Override
    public Place findByName(String placeName) {
        return null;
    }

    @Override
    public List<Place> findByLocation(String region) {
        return null;
    }

    @Override
    public List<Place> findByCuisineType(CuisineType type) {
        return null;
    }

    @Override
    public List<Place> findByType(String type) {
        return null;
    }
}
