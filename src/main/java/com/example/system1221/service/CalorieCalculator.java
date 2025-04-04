package com.example.system1221.service;

import com.example.system1221.dao.user.UserEntity;
import com.example.system1221.enums.GoalEnum;
import org.springframework.stereotype.Service;

@Service
public class CalorieCalculator {
    public double calculateDailyCalories(UserEntity user) {
        double bmr = calculateBMR(user);
        return applyGoalMultiplier(bmr, user.getGoal());
    }

    private double calculateBMR(UserEntity user) {
        return 66.5 + (13.75 * user.getWeight()) +
                (5.003 * user.getHeight()) - (6.755 * user.getAge());
    }

    private double applyGoalMultiplier(double bmr, GoalEnum goal) {
        return switch (goal) {
            case WEIGHT_LOSS -> bmr * 0.85;
            case WEIGHT_GAIN -> bmr * 1.15;
            default -> bmr;
        };
    }
}
