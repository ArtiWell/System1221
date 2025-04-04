package com.example.system1221.dto;

import com.example.system1221.dao.user.UserEntity;
import com.example.system1221.enums.GoalEnum;

public record UserDto(
        Long id,
        String name,
        String email,
        int age,
        double weight,
        double height,
        GoalEnum goal,
        double dailyCalorieNorm
) {
    public static UserDto fromEntity(UserEntity user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getWeight(),
                user.getHeight(),
                user.getGoal(),
                user.getDailyCalorieNorm()
        );
    }
}
