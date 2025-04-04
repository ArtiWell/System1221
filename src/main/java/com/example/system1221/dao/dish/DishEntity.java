package com.example.system1221.dao.dish;

import com.example.system1221.dao.meal.MealEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dishes")
@Setter
@Getter
@NoArgsConstructor
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "calories", nullable = false)
    private double calories;

    @Column(name = "proteins", nullable = false)
    private double proteins;

    @Column(name = "fats", nullable = false)
    private double fats;

    @Column(name = "carbs", nullable = false)
    private double carbs;

    @ManyToMany(mappedBy = "dishes")
    private List<MealEntity> meals = new ArrayList<>();
}
