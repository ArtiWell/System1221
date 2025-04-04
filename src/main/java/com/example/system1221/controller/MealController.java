package com.example.system1221.controller;

import com.example.system1221.dao.meal.MealEntity;
import com.example.system1221.dto.MealDTO;
import com.example.system1221.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meals")
public class MealController {
    private final MealService mealService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealDTO addMeal(@RequestBody MealDTO mealDTO) {
        return mealService.addMeal(mealDTO);
    }

    @GetMapping("/user/{userId}")
    public List<MealEntity> getMealsByUser(@PathVariable Long userId) {
        return mealService.getMealsByUser(userId);
    }

    @GetMapping("/user/{userId}/date/{date}")
    public List<MealEntity> getMealsByUserAndDate(@PathVariable Long userId,
                                                  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate date) {
        return mealService.getMealsByUserAndDate(userId, date);
    }
}
