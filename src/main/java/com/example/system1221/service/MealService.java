package com.example.system1221.service;

import com.example.system1221.dao.dish.DishEntity;
import com.example.system1221.dao.dish.DishRepository;
import com.example.system1221.dao.meal.MealEntity;
import com.example.system1221.dao.meal.MealRepository;
import com.example.system1221.dao.user.UserEntity;
import com.example.system1221.dao.user.UserRepository;
import com.example.system1221.dto.MealDTO;
import com.example.system1221.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;

    public MealDTO addMeal(MealDTO dto) {
        UserEntity user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException(dto.userId()));

        MealEntity meal = new MealEntity();
        meal.setUser(user);
        meal.setMealType(dto.mealType());
        meal.setDate(dto.date());
        List<DishEntity> dishes = dishRepository.findAllById(dto.dishIds());
        meal.setDishes(dishes);

        return MealDTO.fromEntity(mealRepository.save(meal));
    }

    public List<MealEntity> getMealsByUserAndDate(Long userId, LocalDate date) {
        return mealRepository.findByUserIdAndDate(userId, date);
    }

    public List<MealEntity> getMealsByUser(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    public double calculateTotalCalories(List<MealEntity> meals) {
        return meals.stream()
                .flatMap(m -> m.getDishes().stream())
                .mapToDouble(DishEntity::getCalories)
                .sum();
    }

    public double calculateTotalProteins(List<MealEntity> meals) {
        return meals.stream()
                .flatMap(m -> m.getDishes().stream())
                .mapToDouble(DishEntity::getProteins)
                .sum();
    }

    public double calculateTotalFats(List<MealEntity> meals) {
        return meals.stream()
                .flatMap(m -> m.getDishes().stream())
                .mapToDouble(DishEntity::getFats)
                .sum();
    }

    public double calculateTotalCarbs(List<MealEntity> meals) {
        return meals.stream()
                .flatMap(m -> m.getDishes().stream())
                .mapToDouble(DishEntity::getCarbs)
                .sum();
    }
}
