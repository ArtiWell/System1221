package com.example.system1221.dao.dish;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<DishEntity, Long> {
    List<DishEntity> findByNameContainingIgnoreCase(String name);
}
