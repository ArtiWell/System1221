package com.example.system1221.service;

import com.example.system1221.dao.dish.DishEntity;
import com.example.system1221.dao.dish.DishRepository;
import com.example.system1221.dto.DishDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public DishDTO createDish(DishDTO dishDto) {
        DishEntity dish = new DishEntity();
        dish.setName(dishDto.name());
        dish.setCalories(dishDto.calories());
        dish.setProteins(dishDto.proteins());
        dish.setFats(dishDto.fats());
        dish.setCarbs(dishDto.carbs());
        return DishDTO.fromEntity(dishRepository.save(dish));
    }

    public List<DishDTO> getAllDishes() {
        return dishRepository.findAll().stream().map(DishDTO::fromEntity).toList();
    }
}