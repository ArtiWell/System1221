package com.example.system1221.dto;

import java.time.LocalDate;
import java.util.List;

public record DailyReportDTO(
        LocalDate date,
        double totalCalories,
        double totalProteins,
        double totalFats,
        double totalCarbs,
        boolean isGoalAchieved,
        List<MealDTO> meals
) {}
