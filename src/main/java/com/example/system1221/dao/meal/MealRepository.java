package com.example.system1221.dao.meal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<MealEntity, Long> {
    List<MealEntity> findByUserIdAndDate(Long userId, LocalDate date);
    List<MealEntity> findByUserId(Long userId);
}
