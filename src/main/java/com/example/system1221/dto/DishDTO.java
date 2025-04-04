package com.example.system1221.dto;

import com.example.system1221.dao.dish.DishEntity;

public record DishDTO(
        Long id,
        String name,
        double calories,
        double proteins,
        double fats,
        double carbs
) {
    public static DishDTO fromEntity(DishEntity dish) {
        return new DishDTO(
                dish.getId(),
                dish.getName(),
                dish.getCalories(),
                dish.getProteins(),
                dish.getFats(),
                dish.getCarbs()
        );
    }
}
