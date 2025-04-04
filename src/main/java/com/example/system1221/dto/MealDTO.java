package com.example.system1221.dto;

import com.example.system1221.dao.dish.DishEntity;
import com.example.system1221.dao.meal.MealEntity;
import com.example.system1221.enums.MealEnum;

import java.time.LocalDate;
import java.util.List;

public record MealDTO(
        Long id,
        Long userId,
        List<Long> dishIds,
        LocalDate date,
        MealEnum mealType
) {
    public static MealDTO fromEntity(MealEntity meal) {
        return new MealDTO(
                meal.getId(),
                meal.getUser().getId(),
                meal.getDishes().stream().map(DishEntity::getId).toList(),
                meal.getDate(),
                meal.getMealType()
        );
    }
}
