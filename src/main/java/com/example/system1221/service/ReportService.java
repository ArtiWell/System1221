package com.example.system1221.service;

import com.example.system1221.dao.meal.MealEntity;
import com.example.system1221.dao.user.UserEntity;
import com.example.system1221.dao.user.UserRepository;
import com.example.system1221.dto.DailyReportDTO;
import com.example.system1221.dto.MealDTO;
import com.example.system1221.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final MealService mealService;
    private final UserRepository userRepository;

    public DailyReportDTO getDailyReport(Long userId, LocalDate date) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        List<MealEntity> meals = mealService.getMealsByUserAndDate(userId, date);

        double totalCalories = mealService.calculateTotalCalories(meals);
        double totalProteins = mealService.calculateTotalProteins(meals);
        double totalFats = mealService.calculateTotalFats(meals);
        double totalCarbs = mealService.calculateTotalCarbs(meals);

        boolean isGoalAchieved = totalCalories <= user.getDailyCalorieNorm();

        List<MealDTO> mealDTOs = meals.stream().map(MealDTO::fromEntity).toList();

        return new DailyReportDTO(
                date,
                totalCalories,
                totalProteins,
                totalFats,
                totalCarbs,
                isGoalAchieved,
                mealDTOs
        );
    }

    public List<DailyReportDTO> getMealHistory(Long userId) {
        return mealService.getMealsByUser(userId).stream()
                .collect(Collectors.groupingBy(MealEntity::getDate))
                .entrySet().stream()
                .map(e -> getDailyReport(userId, e.getKey()))
                .toList();
    }

    public boolean isWithinCalorieLimit(Long userId, LocalDate date) {
        DailyReportDTO report = getDailyReport(userId, date);
        return report.totalCalories() <= userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId)).getDailyCalorieNorm();
    }
}
