package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.CuisineType;
import com.alevel.project.coffee.repository.CuisineTypeRepo;
import com.alevel.project.coffee.service.CuisineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuisineTypeServiceImpl implements CuisineTypeService {

    private final CuisineTypeRepo cuisineTypeRepo;

    @Autowired
    public CuisineTypeServiceImpl(CuisineTypeRepo cuisineTypeRepo) {
        this.cuisineTypeRepo = cuisineTypeRepo;
    }

    @Override
    public List<CuisineType> findAll() {
        return cuisineTypeRepo.findAll();
    }

    @Override
    public Optional<CuisineType> findById(Integer id) {
        return cuisineTypeRepo.findById(id);
    }

    @Override
    public Optional<CuisineType> findByCuisineType(String cuisineType) {
        return cuisineTypeRepo.findByCuisineType(cuisineType);
    }
}
