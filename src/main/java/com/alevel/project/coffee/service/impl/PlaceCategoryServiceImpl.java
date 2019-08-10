package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.PlaceCategory;
import com.alevel.project.coffee.repository.PlaceCategoryRepo;
import com.alevel.project.coffee.service.PlaceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceCategoryServiceImpl implements PlaceCategoryService {

    private final PlaceCategoryRepo placeCategoryRepo;

    @Autowired
    public PlaceCategoryServiceImpl(PlaceCategoryRepo placeCategoryRepo) {
        this.placeCategoryRepo = placeCategoryRepo;
    }

    @Override
    public List<PlaceCategory> findAll() {
        return placeCategoryRepo.findAll();
    }

    @Override
    public Optional<PlaceCategory> findById(Integer id) {
        return placeCategoryRepo.findById(id);
    }

    @Override
    public Optional<PlaceCategory> findByPlaceCategory(String placeCategory) {
        return placeCategoryRepo.findByPlaceCategory(placeCategory);
    }
}
