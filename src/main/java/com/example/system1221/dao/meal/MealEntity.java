package com.example.system1221.dao.meal;

import com.example.system1221.dao.dish.DishEntity;
import com.example.system1221.dao.user.UserEntity;
import com.example.system1221.enums.MealEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "meals")
@NoArgsConstructor
public class MealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToMany
    @JoinTable(name = "meal_dishes",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<DishEntity> dishes = new ArrayList<>();

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "mealType", nullable = false)
    @Enumerated(EnumType.STRING)
    private MealEnum mealType;
}
