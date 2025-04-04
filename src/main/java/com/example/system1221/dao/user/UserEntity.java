package com.example.system1221.dao.user;


import com.example.system1221.dao.meal.MealEntity;
import com.example.system1221.enums.GoalEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "height", nullable = false)
    private double height;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal", nullable = false)
    private GoalEnum goal;

    @Column(name = "daily_calorie_norm", nullable = false)
    private double dailyCalorieNorm;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealEntity> meals = new ArrayList<>();

}

